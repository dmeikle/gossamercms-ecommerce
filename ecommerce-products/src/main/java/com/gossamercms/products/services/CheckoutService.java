package com.gossamercms.products.services;

import com.gossamercms.mvc.annotations.ModuleService;
import com.gossamercms.mvc.jwt.CurrentJwtUser;
import com.gossamercms.products.data.PurchaseAddressesDbService;
import com.gossamercms.products.data.PurchaseItemsDbService;
import com.gossamercms.products.data.PurchasePaymentsDbService;
import com.gossamercms.products.data.PurchasesDbService;
import com.gossamercms.products.dtos.PurchaseAddressDto;
import com.gossamercms.products.dtos.PurchaseDto;
import com.gossamercms.products.dtos.PurchaseItemDto;
import com.gossamercms.products.dtos.PurchasePaymentDto;
import com.gossamercms.products.dtos.requests.CheckoutRequest;
import com.gossamercms.products.dtos.requests.PurchaseAddressRequest;
import com.gossamercms.products.dtos.responses.*;
import com.gossamercms.products.enums.PurchaseAddressAddressType;
import com.gossamercms.products.enums.PurchasePaymentStatus;
import com.gossamercms.stripe.payments.config.StripeCheckoutProperties;
import com.gossamercms.stripe.payments.dtos.requests.TaxCalculationRequest;
import com.gossamercms.stripe.payments.dtos.responses.TaxCalculationResponse;
import com.gossamercms.stripe.payments.payments.PaymentAdapter;
import com.gossamercms.stripe.payments.services.StripeTaxCalculationService;
import lombok.RequiredArgsConstructor;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ModuleService
@RequiredArgsConstructor
public class CheckoutService {

    private final CartsService cartService;
    private final StripeTaxCalculationService stripeTaxCalculationService;
    private final PurchasesDbService purchasesDbService;
    private final PurchaseItemsDbService purchaseItemsDbService;
    private final PaymentAdapter paymentAdapter;
    private final StripeCheckoutProperties checkoutProperties;
    private final PurchaseAddressesDbService purchaseAddressesDbService;
    private final PurchasePaymentsDbService purchasePaymentsDbService;

    public CheckoutResponse checkout(
            CurrentJwtUser jwtUser,
            CheckoutRequest request
    ) {

        // Step 1: Load cart
        CartResponse cart = jwtUser.getUserContextId() != null
                ? cartService.getCartByUserContextId(jwtUser.getUserContextId())
                : cartService.getCartBySessionId(jwtUser.getSessionId());

        // Step 2: Validate cart
        // validateCart(cart);

        // Step 3: Calculate pricing
        CartSummaryResponse pricingDetails = cart.getSummary();


        // Step 4: Calculate shipping
        PurchaseAddressDto shippingAddress =
                request.getPurchaseAddress(
                        PurchaseAddressAddressType.SHIPPING.toString()
                );
        ShippingDetailsResponse shippingDetails =
                calculateShipping(cart, shippingAddress);

        // Step 5: Calculate taxes
        TaxCalculationResponse taxDetails =
                calculateTaxes(cart, shippingDetails, shippingAddress);

        // Step 6: Create purchase and purchase items
        PurchasePreparation preparation = createPurchase(
                jwtUser,
                cart,
                pricingDetails,
                shippingDetails,
                taxDetails,
                request.getAddresses()
        );

        PurchaseDto purchase = preparation.purchase();

        // Step 7: Reserve inventory
        // reserveInventory(purchase);

        // Step 8: Initialize payment
        PaymentInfo paymentInfo = initializePayment(
                jwtUser,
                request,
                purchase,
                preparation.items(),
                request.getPaymentMethod()
        );

        // Step 9: Return checkout response
        return new CheckoutResponse(purchase, paymentInfo);
    }

    /**
     * Initializes payment using the configured payment provider.
     */
    private PaymentInfo initializePayment(
            CurrentJwtUser jwtUser,
            CheckoutRequest request,
            PurchaseDto purchase,
            List<PurchaseItemDto> purchaseItems,
            String paymentMethod
    ) {

        if (purchaseItems == null || purchaseItems.isEmpty()) {
            throw new IllegalStateException(
                    "Cannot initialize payment without purchase items"
            );
        }

        // Convert purchase items to payment adapter line items.
        List<PaymentAdapter.Line> lines = purchaseItems.stream()
                .map(item -> new PaymentAdapter.Line(
                        item.getProductId(),
                        item.getProductName(),
                        item.getQuantity(),
                        item.getUnitPrice()
                ))
                .toList();

        // Build payment checkout request.
        PaymentAdapter.CheckoutRequest checkoutRequest =
                new PaymentAdapter.CheckoutRequest(
                        purchase.getId(),
                        request.getCustomerName(),
                        request.getCustomerEmail(),
                        purchase.getCurrency(),
                        purchase.getTotalAmount(),
                        lines,
                        checkoutProperties.getSuccessUrl(),
                        checkoutProperties.getCancelUrl()
                );

        // Create checkout session with payment provider.
        PaymentAdapter.Checkout checkout =
                paymentAdapter.createCheckout(checkoutRequest);

        if (checkout == null
                || checkout.checkoutId() == null
                || checkout.checkoutUrl() == null) {
            throw new IllegalStateException(
                    "Payment provider did not return a valid checkout session"
            );
        }

        purchasePaymentsDbService.create(
                jwtUser.getUserContextId(),
                PurchasePaymentDto.builder()
                        .purchaseId(purchase.getId())
                        .provider(paymentAdapter.provider())
                        .paymentMethod(paymentMethod)
                        .providerCheckoutId(checkout.checkoutId())
                        .amount(purchase.getTotalAmount())
                        .currency(purchase.getCurrency())
                        .status(PurchasePaymentStatus.PENDING.toString())
                        .build()
        );

        return PaymentInfo.builder()
                .provider(paymentAdapter.provider())
                .checkoutId(checkout.checkoutId())
                .checkoutUrl(checkout.checkoutUrl())
                .paymentMethod(paymentMethod)
                .build();
    }

    /**
     * Creates the purchase and its purchase item snapshots.
     */
    private PurchasePreparation createPurchase(
            CurrentJwtUser jwtUser,
            CartResponse cart,
            CartSummaryResponse pricingDetails,
            ShippingDetailsResponse shippingDetails,
            TaxCalculationResponse taxDetails,
            List<PurchaseAddressRequest> addresses
    ) {

        PurchaseDto purchase = purchasesDbService.create(
                jwtUser.getUserContextId(),
                PurchaseDto.builder()
                        .currency(pricingDetails.getCurrency())
                        .purchaseNumber(getPurchaseNumber())
                        .subtotal(pricingDetails.getSubtotal())
                        .discountTotal(pricingDetails.getDiscountTotal())
                        .shippingTotal(shippingDetails.getShippingCost())
                        .taxTotal(taxDetails.getTotalTax())
                        .status(PurchasePaymentStatus.PENDING.toString())
                        .totalAmount(pricingDetails.getGrandTotal())
                        .build()
        );

        // Save purchase addresses
        if (addresses != null) {
            for (PurchaseAddressRequest address : addresses) {
                purchaseAddressesDbService.create(
                        jwtUser.getUserContextId(),
                        PurchaseAddressDto.builder()
                                .firstName(address.getFirstName())
                                .lastName(address.getLastName())
                                .company(address.getCompany())
                                .purchaseId(purchase.getId())
                                .addressType(address.getAddressType())
                                .addressLine1(address.getAddressLine1())
                                .addressLine2(address.getAddressLine2())
                                .city(address.getCity())
                                .province(address.getProvince())
                                .postalCode(address.getPostalCode())
                                .telephone(address.getTelephone())
                                .country(address.getCountry())
                                .build()
                );
            }
        }

        List<PurchaseItemDto> purchaseItems = new ArrayList<>();

        cart.getItems().forEach(item -> {

            PurchaseItemDto purchaseItem =
                    purchaseItemsDbService.create(
                            jwtUser.getUserContextId(),
                            PurchaseItemDto.builder()
                                    .currency(pricingDetails.getCurrency())
                                    .purchaseId(purchase.getId())
                                    .productId(item.getProductId())
                                    .productName(item.getProduct() != null
                                            ? item.getProduct().getName()
                                            : null)
                                    .quantity(item.getQuantity())
                                    .unitPrice(item.getUnitPrice())
                                    .totalPrice(item.getSubtotal())
                                    .metadata(item.getProduct() != null
                                            ? item.getProduct().getMetadata()
                                            : null)
                                    .build()
                    );

            purchaseItems.add(purchaseItem);
        });

        return new PurchasePreparation(purchase, purchaseItems);
    }

    private String getPurchaseNumber() {

        Long sequenceNumber =
                purchasesDbService.getNextPurchaseSequenceValue();

        return String.format(
                "ORD-%d-%06d",
                Year.now().getValue(),
                sequenceNumber
        );
    }

    /**
     * Calculates taxes for the cart.
     */
    private TaxCalculationResponse calculateTaxes(
            CartResponse cart,
            ShippingDetailsResponse shippingDetails,
            PurchaseAddressDto shippingAddress
    ) {

        return stripeTaxCalculationService.calculate(
                TaxCalculationRequest.builder()
                        .currency(cart.getSummary().getCurrency())
                        .customerDetails(
                                TaxCalculationRequest.CustomerDetails.builder()
                                        .address(
                                                TaxCalculationRequest.Address.builder()
                                                        .line1(shippingAddress.getAddressLine1())
                                                        .line2(shippingAddress.getAddressLine2())
                                                        .city(shippingAddress.getCity())
                                                        .state(shippingAddress.getProvince())
                                                        .postalCode(shippingAddress.getPostalCode())
                                                        .country(shippingAddress.getCountry())
                                                        .build()
                                        )
                                        .addressSource(
                                                TaxCalculationRequest.AddressSource.SHIPPING
                                        )
                                        .build()
                        )
                        .lineItems(
                                cart.getItems().stream()
                                        .map(item ->
                                                TaxCalculationRequest.LineItem.builder()
                                                        .amount(item.getSubtotal())
                                                        .taxCode(item.getTaxCode())
                                                        .reference(item.getProductId().toString())
                                                        .build()
                                        )
                                        .toList()
                        )
                        .shippingAmount(shippingDetails.getShippingCost())
                        .build()
        );
    }

    private ShippingDetailsResponse calculateShipping(
            CartResponse cart,
            PurchaseAddressDto shippingAddress
    ) {
        // Implement shipping calculation logic here.
        return  ShippingDetailsResponse.builder()
                .shippingCost(cart.getSummary().getShippingTotal())
                .build();
    }

    /**
     * Holds the purchase and the items created for it.
     */
    private record PurchasePreparation(
            PurchaseDto purchase,
            List<PurchaseItemDto> items
    ) {}
}