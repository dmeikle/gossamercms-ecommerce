package com.gossamercms.products.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasePaymentRequest {
    private UUID purchaseId;
    private String provider;
    private String paymentMethod;
    private String providerPaymentId;
    private String providerCustomerId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String failureCode;
    private String failureMessage;
    private Instant paidAt;
    private String idempotencyKey;
    private Map<String, Object> metadata;

}
