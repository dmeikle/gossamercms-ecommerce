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
public class PurchaseRefundListResponse {
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
    private Instant createdAt;

}