package com.gossamercms.products.services;

import com.gossamercms.mvc.jwt.CurrentJwtUser;
import com.gossamercms.products.data.PurchaseAddressesDbService;
import com.gossamercms.products.data.PurchaseItemsDbService;
import com.gossamercms.products.data.PurchasePaymentsDbService;
import com.gossamercms.products.data.PurchasesDbService;
import com.gossamercms.products.dtos.ProductDto;
import com.gossamercms.products.dtos.PurchaseAddressDto;
import com.gossamercms.products.dtos.PurchaseDto;
import com.gossamercms.products.dtos.PurchaseItemDto;
import com.gossamercms.products.dtos.PurchasePaymentDto;
import com.gossamercms.products.dtos.requests.CheckoutRequest;
import com.gossamercms.products.dtos.responses.CartItemResponse;
import com.gossamercms.products.dtos.responses.CartResponse;
import com.gossamercms.products.dtos.responses.CartSummaryResponse;
import com.gossamercms.products.enums.PurchasePaymentStatus;
import com.gossamercms.stripe.payments.config.StripeCheckoutProperties;
import com.gossamercms.stripe.payments.dtos.responses.TaxCalculationResponse;
import com.gossamercms.stripe.payments.payments.PaymentAdapter;
import com.gossamercms.stripe.payments.services.StripeTaxCalculationService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CheckoutServiceTest {
    @Test
    void createsPendingPaymentWithProviderCheckoutId() {
        CartsService carts = mock(CartsService.class);
        StripeTaxCalculationService taxes = mock(StripeTaxCalculationService.class);
        PurchasesDbService purchases = mock(PurchasesDbService.class);
        PurchaseItemsDbService items = mock(PurchaseItemsDbService.class);
        PurchasePaymentsDbService payments = mock(PurchasePaymentsDbService.class);
        PaymentAdapter adapter = mock(PaymentAdapter.class);
        CurrentJwtUser user = mock(CurrentJwtUser.class);
        CheckoutRequest request = mock(CheckoutRequest.class);
        UUID userId = UUID.randomUUID();
        UUID purchaseId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        BigDecimal amount = new BigDecimal("25.00");
        when(user.getUserContextId()).thenReturn(userId);
        when(request.getPurchaseAddress(any())).thenReturn(PurchaseAddressDto.builder().build());
        when(carts.getCartByUserContextId(userId)).thenReturn(CartResponse.builder()
                .summary(CartSummaryResponse.builder().currency("CAD").subtotal(amount)
                        .grandTotal(amount).shippingTotal(BigDecimal.ZERO).build())
                .items(List.of(CartItemResponse.builder().productId(productId).quantity(1)
                        .unitPrice(amount).subtotal(amount).product(ProductDto.builder().name("Item").build()).build()))
                .build());
        when(taxes.calculate(any())).thenReturn(TaxCalculationResponse.builder().totalTax(BigDecimal.ZERO).build());
        when(purchases.getNextPurchaseSequenceValue()).thenReturn(1L);
        when(purchases.create(eq(userId), any())).thenReturn(PurchaseDto.builder()
                .id(purchaseId).currency("CAD").totalAmount(amount).build());
        when(items.create(eq(userId), any())).thenReturn(PurchaseItemDto.builder()
                .productId(productId).productName("Item").quantity(1).unitPrice(amount).build());
        when(adapter.provider()).thenReturn("stripe");
        when(adapter.createCheckout(any())).thenReturn(new PaymentAdapter.Checkout("cs_123", "https://checkout.test"));
        CheckoutService service = new CheckoutService(carts, taxes, purchases, items, adapter,
                new StripeCheckoutProperties(), mock(PurchaseAddressesDbService.class), payments);

        service.checkout(user, request);

        ArgumentCaptor<PurchasePaymentDto> payment = ArgumentCaptor.forClass(PurchasePaymentDto.class);
        verify(payments).create(eq(userId), payment.capture());
        assertEquals(purchaseId, payment.getValue().getPurchaseId());
        assertEquals("cs_123", payment.getValue().getProviderCheckoutId());
        assertEquals(PurchasePaymentStatus.PENDING.toString(), payment.getValue().getStatus());
        assertNull(payment.getValue().getProviderPaymentId());
        assertNull(payment.getValue().getProviderCustomerId());
        assertNull(payment.getValue().getIdempotencyKey());
    }
}
