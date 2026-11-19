package com.gossamercms.products.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTaxRequest {
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

}
