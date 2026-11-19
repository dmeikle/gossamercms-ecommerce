package com.gossamercms.products.services;

import com.gossamercms.mvc.annotations.ModuleService;
import com.gossamercms.mvc.data.QueryOptions;
import com.gossamercms.mvc.jwt.CurrentJwtUser;
import com.gossamercms.outbox.services.OutboxService;
import com.gossamercms.products.data.PurchaseItemsDbService;
import com.gossamercms.products.data.PurchasePaymentsDbService;
import com.gossamercms.products.data.PurchaseTransaction;
import com.gossamercms.products.data.PurchasesDbService;
import com.gossamercms.products.dtos.PurchaseDto;
import com.gossamercms.products.dtos.PurchaseItemDto;
import com.gossamercms.products.dtos.PurchasePaymentDto;
import com.gossamercms.products.enums.PurchasePaymentStatus;
import com.gossamercms.products.events.PurchaseCreatedEvent;
import com.gossamercms.stripe.payments.services.PaymentCompletionService;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import gossamercms.globals.config.SystemPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@ModuleService
@RequiredArgsConstructor
public class PurchasePaymentCompletionService implements PaymentCompletionService {

    private final PurchaseTransaction purchaseTransaction;
    private final PurchasesDbService purchasesDbService;
    private final PurchasePaymentsDbService paymentsDbService;
    private final PurchaseItemsDbService itemsDbService;
//    private final InventoryReservationService inventoryReservationService;
//    private final FulfillmentService fulfillmentService;
    private final OutboxService outboxService;
    private final ObjectProvider<StripeClient> stripeClient;

    // Call only after a trusted payment provider has verified
    // payment or cancellation.
    public void complete(
            UUID purchaseId,
            String provider,
            String checkoutId,
            String paymentId,
            BigDecimal amount,
            String currency,
            boolean cancelled
    ) {
        purchaseTransaction.transaction(() -> {

            // Lock the purchase while processing the payment completion.
            PurchaseDto purchase =
                    purchasesDbService.purchase(purchaseId, true);

            PurchasePaymentDto payment =
                    paymentsDbService.payment(purchaseId);

            // Verify that the provider response matches our persisted checkout.
            if (purchase == null || payment == null
                    || !Objects.equals(provider, payment.getProvider())
                    || checkoutId == null || checkoutId.isBlank()
                    || !Objects.equals(checkoutId, payment.getProviderCheckoutId())
                    || amount == null
                    || payment.getAmount() == null
                    || purchase.getTotalAmount() == null
                    || amount.compareTo(payment.getAmount()) != 0
                    || amount.compareTo(purchase.getTotalAmount()) != 0
                    || !Objects.equals(currency, payment.getCurrency())
                    || !Objects.equals(currency, purchase.getCurrency())) {

                throw conflict("Checkout does not match the persisted payment");
            }

            // Idempotency: repeated cancellation notification.
            if (cancelled
                    && PurchasePaymentStatus.CANCELLED.toString().equals(purchase.getStatus())
                    && PurchasePaymentStatus.CANCELLED.toString().equals(payment.getStatus())) {
                return null;
            }

            // Idempotency: repeated successful payment notification.
            if (!cancelled
                    && PurchasePaymentStatus.PAID.toString().equals(purchase.getStatus())
                    && PurchasePaymentStatus.PAID.toString().equals(payment.getStatus())
                    && Objects.equals(paymentId, payment.getProviderPaymentId())) {
                return null;
            }

            // Only pending payments can be completed.
            if (!PurchasePaymentStatus.PENDING.toString().equals(purchase.getStatus())
                    || !PurchasePaymentStatus.PENDING.toString().equals(payment.getStatus())) {
                throw conflict("Purchase is not awaiting payment");
            }

            if (!cancelled
                    && (paymentId == null
                    || paymentId.isBlank()
                    || paymentId.length() > 255)) {
                throw conflict("A confirmed provider payment ID is required");
            }

            // Do not accept a successful payment against an expired checkout.
//            if (!cancelled
//                    && purchase.getExpiresAt() != null
//                    && !Instant.now().isBefore(purchase.getExpiresAt())) {
//                throw conflict("Checkout has expired");
//            }

            List<PurchaseItemDto> items =
                    itemsDbService.getAll(QueryOptions.builder()
                                    .filters(Map.of("purchaseId", purchaseId))
                            .build()).list();

            if (cancelled) {

                // Release previously reserved stock.
               // inventoryReservationService.releaseReservation(purchaseId);

                paymentsDbService.completeCancellation(SystemPrincipal.ID, purchaseId);
                purchasesDbService.completeCancellation(SystemPrincipal.ID, purchaseId);

            } else {
                // Mark the payment as successfully completed.
                paymentsDbService.completePayment(
                        payment.getId(),
                        paymentId,
                        customerId(provider, checkoutId)
                );

                // Update purchase payment status and move it into processing.
                purchasesDbService.completePurchase(SystemPrincipal.ID, PurchaseDto.builder()
                                .status(PurchasePaymentStatus.PAID.toString())
                                .build(),
                        Map.of("id", purchaseId));

                // Commit the previously reserved inventory.
               // inventoryReservationService.completeReservation(purchaseId);

                // Reload so fulfillment receives the updated purchase.
                PurchaseDto completedPurchase =
                        purchasesDbService.purchase(purchaseId, false);

                outboxService.publish(
                        "purchase.created",
                        purchaseId,
                        PurchaseCreatedEvent.builder()
                                .purchase(completedPurchase)
                                .items(List.copyOf(items))
                                .build()
                );
            }

            return null;
        });
    }

    private static ResponseStatusException conflict(String message) {
        return new ResponseStatusException(
                HttpStatus.CONFLICT,
                message
        );
    }

    private String customerId(String provider, String checkoutId) {
        if (!"stripe".equals(provider)) {
            return null;
        }
        try {
            return stripeClient.getObject().v1().checkout().sessions()
                    .retrieve(checkoutId).getCustomer();
        } catch (StripeException e) {
            throw new IllegalStateException("Failed to retrieve Stripe Checkout Session", e);
        }
    }


}