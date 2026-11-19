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
public class PurchaseTaxListResponse {
    private UUID purchaseId;
    private UUID purchaseItemId;
    private String taxName;
    private String taxType;
    private String jurisdiction;
    private String jurisdictionCode;
    private BigDecimal taxRate;
    private BigDecimal taxableAmount;
    private BigDecimal taxAmount;
    private String provider;
    private String providerReference;
    private Instant createdAt;

}