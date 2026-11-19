package com.gossamercms.products.models;

import com.gossamercms.products.dtos.PurchaseTaxDto;
import com.gossamercms.mvc.models.BaseModel;
import com.gossamercms.mvc.models.ModelMeta;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTax implements BaseModel{
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

    public PurchaseTaxDto toDto() {
        return PurchaseTaxDto.builder()
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


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_purchase_taxes")
    .column("id", UUID.class)
    .column("purchaseId", UUID.class)
    .column("purchaseItemId", UUID.class)
    .column("taxName", String.class, 100)
    .column("taxType", String.class, 50)
    .column("jurisdiction", String.class, 150)
    .column("jurisdictionCode", String.class, 50)
    .column("taxRate", BigDecimal.class)
    .column("taxableAmount", BigDecimal.class)
    .column("taxAmount", BigDecimal.class)
    .column("provider", String.class, 50)
    .column("providerReference", String.class, 255)
    .column("createdAt", Instant.class)
    .defaultSort("id desc")
    .build();



    @Override
    public ModelMeta meta() {
        return META;
    }

    @Override
    public ModelMeta metaOf() {
        return BaseModel.super.metaOf();
    }

}
