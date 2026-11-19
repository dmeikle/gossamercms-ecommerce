package com.gossamercms.products.dtos.responses;

import com.gossamercms.products.dtos.PurchaseDto;

public record CheckoutResponse(PurchaseDto purchase, PaymentInfo paymentInfo) {
}
