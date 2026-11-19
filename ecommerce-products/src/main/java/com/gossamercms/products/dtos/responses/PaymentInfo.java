package com.gossamercms.products.dtos.responses;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInfo {

    private String provider;
    private String checkoutId;
    private String checkoutUrl;
    private String paymentMethod;
}
