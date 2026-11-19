package com.gossamercms.products.dtos;

import com.gossamercms.products.models.PurchaseTax;
import com.gossamercms.mvc.data.DtoWithId;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTaxDto implements DtoWithId{
    private UUID id;
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

    public PurchaseTax toEntity() {
        return PurchaseTax.builder()
            .id(id)
            .purchaseId(purchaseId)
            .purchaseItemId(purchaseItemId)
            .taxName(taxName)
            .taxType(taxType)
            .jurisdiction(jurisdiction)
            .jurisdictionCode(jurisdictionCode)
            .taxRate(taxRate)
            .taxableAmount(taxableAmount)
            .taxAmount(taxAmount)
            .provider(provider)
            .providerReference(providerReference)
            .createdAt(createdAt)
            .build();
    }
}
