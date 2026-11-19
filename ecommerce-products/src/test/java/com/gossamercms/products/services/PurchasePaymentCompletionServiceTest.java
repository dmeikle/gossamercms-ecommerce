package com.gossamercms.products.services;

import com.gossamercms.mvc.data.ListResultset;
import com.gossamercms.mvc.data.QueryOptions;
import com.gossamercms.outbox.services.OutboxService;
import com.gossamercms.products.data.PurchaseItemsDbService;
import com.gossamercms.products.data.PurchasePaymentsDbService;
import com.gossamercms.products.data.PurchaseTransaction;
import com.gossamercms.products.data.PurchasesDbService;
import com.gossamercms.products.dtos.PurchaseDto;
import com.gossamercms.products.dtos.PurchasePaymentDto;
import com.gossamercms.products.enums.PurchasePaymentStatus;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PurchasePaymentCompletionServiceTest {
    private final UUID purchaseId = UUID.randomUUID();
    private final UUID paymentId = UUID.randomUUID();
    private final BigDecimal amount = new BigDecimal("25.00");
    private final PurchaseTransaction transaction = mock(PurchaseTransaction.class);
    private final PurchasesDbService purchases = mock(PurchasesDbService.class);
    private final PurchasePaymentsDbService payments = mock(PurchasePaymentsDbService.class);
    private final PurchaseItemsDbService items = mock(PurchaseItemsDbService.class);
    private final OutboxService outbox = mock(OutboxService.class);
    private final StripeClient stripe = mock(StripeClient.class, RETURNS_DEEP_STUBS);
    @SuppressWarnings("unchecked")
    private final ObjectProvider<StripeClient> stripeProvider = mock(ObjectProvider.class);
    private final PurchasePaymentCompletionService service =
            new PurchasePaymentCompletionService(transaction, purchases, payments, items, outbox, stripeProvider);

    @BeforeEach
    void setUp() {
        when(transaction.transaction(any())).thenAnswer(invocation ->
                invocation.<Supplier<?>>getArgument(0).get());
        when(purchases.purchase(purchaseId, true)).thenReturn(PurchaseDto.builder()
                .id(purchaseId).status(PurchasePaymentStatus.PENDING.toString())
                .totalAmount(amount).currency("CAD").build());
        when(payments.payment(purchaseId)).thenReturn(PurchasePaymentDto.builder()
                .id(paymentId).provider("stripe").providerCheckoutId("cs_123")
                .status(PurchasePaymentStatus.PENDING.toString())
                .amount(amount).currency("CAD").build());
        when(items.getAll(any(QueryOptions.class))).thenReturn(ListResultset.of(List.of(), 1, 100, 0));
        when(stripeProvider.getObject()).thenReturn(stripe);
    }

    @Test
    void recordsPaymentAndCustomerFromConfirmedCheckout() throws StripeException {
        Session session = mock(Session.class);
        when(session.getCustomer()).thenReturn("cus_123");
        when(stripe.v1().checkout().sessions().retrieve("cs_123")).thenReturn(session);

        service.complete(purchaseId, "stripe", "cs_123", "pi_123", amount, "CAD", false);

        verify(payments).completePayment(paymentId, "pi_123", "cus_123");
        verify(purchases).completePurchase(any(), any(), eq(java.util.Map.of("id", purchaseId)));
        verify(outbox).publish(eq("purchase.created"), eq(purchaseId), any());
    }

    @Test
    void mockProviderDoesNotRequireStripeClient() {
        when(payments.payment(purchaseId)).thenReturn(PurchasePaymentDto.builder()
                .id(paymentId).provider("stripe-mock").providerCheckoutId("mock_123")
                .status(PurchasePaymentStatus.PENDING.toString())
                .amount(amount).currency("CAD").build());

        service.complete(purchaseId, "stripe-mock", "mock_123", "pi_123", amount, "CAD", false);

        verify(payments).completePayment(paymentId, "pi_123", null);
        verifyNoInteractions(stripeProvider);
    }

    @Test
    void stripeGuestCheckoutMayNotHaveCustomer() throws StripeException {
        Session session = mock(Session.class);
        when(stripe.v1().checkout().sessions().retrieve("cs_123")).thenReturn(session);

        service.complete(purchaseId, "stripe", "cs_123", "pi_123", amount, "CAD", false);

        verify(payments).completePayment(paymentId, "pi_123", null);
    }

    @Test
    void rejectsWrongCheckoutBeforeLookingUpCustomer() {
        assertThrows(ResponseStatusException.class, () ->
                service.complete(purchaseId, "stripe", "cs_wrong", "pi_123", amount, "CAD", false));

        verifyNoInteractions(stripeProvider);
        verify(payments, never()).completePayment(any(), any(), any());
    }

    @Test
    void customerLookupFailureDoesNotCompletePayment() throws StripeException {
        when(stripe.v1().checkout().sessions().retrieve("cs_123")).thenThrow(mock(StripeException.class));

        assertThrows(IllegalStateException.class, () ->
                service.complete(purchaseId, "stripe", "cs_123", "pi_123", amount, "CAD", false));

        verify(payments, never()).completePayment(any(), any(), any());
        verifyNoInteractions(outbox);
    }

    @Test
    void duplicateCompletionDoesNotUpdateAgain() {
        when(purchases.purchase(purchaseId, true)).thenReturn(PurchaseDto.builder()
                .id(purchaseId).status(PurchasePaymentStatus.PAID.toString())
                .totalAmount(amount).currency("CAD").build());
        when(payments.payment(purchaseId)).thenReturn(PurchasePaymentDto.builder()
                .id(paymentId).provider("stripe").providerCheckoutId("cs_123")
                .providerPaymentId("pi_123").status(PurchasePaymentStatus.PAID.toString())
                .amount(amount).currency("CAD").build());

        service.complete(purchaseId, "stripe", "cs_123", "pi_123", amount, "CAD", false);

        verify(payments, never()).completePayment(any(), any(), any());
        verifyNoInteractions(stripeProvider, outbox);
    }
}
