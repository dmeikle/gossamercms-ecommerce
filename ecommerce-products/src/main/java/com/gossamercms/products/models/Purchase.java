package com.gossamercms.products.models;

import com.gossamercms.mvc.helpers.annotations.JsonColumn;
import com.gossamercms.products.dtos.PurchaseDto;
import com.gossamercms.mvc.models.BaseModel;
import com.gossamercms.mvc.models.ModelMeta;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase implements BaseModel{
    private UUID id;
    private String purchaseNumber;
    private UUID customerContextId;
    private String status;
    private BigDecimal totalAmount;
    private String currency;
    @JsonColumn
    private Map<String, Object> metadata;
    private UUID createdBy;
    private Instant createdAt;
    private UUID updatedBy;
    private Instant updatedAt;
    private UUID deletedBy;
    private Instant deletedAt;

    public PurchaseDto toDto() {
        return PurchaseDto.builder()
            .id(id)
            .purchaseNumber(purchaseNumber)
            .customerContextId(customerContextId)
            .status(status)
            .totalAmount(totalAmount)
            .currency(currency)
            .metadata(metadata)
            .createdBy(createdBy)
            .createdAt(createdAt)
            .updatedBy(updatedBy)
            .updatedAt(updatedAt)
            .deletedBy(deletedBy)
            .deletedAt(deletedAt)
            .build();
    }


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_purchases")
    .column("id", UUID.class)
    .column("purchaseNumber", String.class, 50)
    .column("customerContextId", UUID.class)
    .column("status", String.class, 20)
    .column("totalAmount", BigDecimal.class)
    .column("currency", String.class, 3)
    .column("metadata", Map.class)
    .column("createdBy", UUID.class)
    .column("createdAt", Instant.class)
    .column("updatedBy", UUID.class)
    .column("updatedAt", Instant.class)
    .column("deletedBy", UUID.class)
    .column("deletedAt", Instant.class)
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
