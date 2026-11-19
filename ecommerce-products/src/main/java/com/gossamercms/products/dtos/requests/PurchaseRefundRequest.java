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
public class PurchaseRefundRequest {
    private UUID purchaseId;
    private UUID paymentId;
    private String providerRefundId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String reason;
    private String description;
    private String provider;
    private String failureCode;
    private String failureMessage;
    private Instant refundedAt;
    private UUID requestedBy;
    private Map<String, Object> metadata;

}
