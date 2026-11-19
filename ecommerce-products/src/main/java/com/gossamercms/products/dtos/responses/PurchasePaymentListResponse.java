package com.gossamercms.products.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasePaymentListResponse {
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
    private Instant createdAt;

}