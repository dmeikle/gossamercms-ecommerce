package com.gossamercms.products.models;

import com.gossamercms.mvc.helpers.annotations.JsonColumn;
import com.gossamercms.products.dtos.PurchaseItemDto;
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
public class PurchaseItem implements BaseModel{
    private UUID id;
    private UUID purchaseId;
    private UUID productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String currency;
    @JsonColumn
    private Map<String, Object> metadata;
    private Instant createdAt;
    private Instant updatedAt;

    public PurchaseItemDto toDto() {
        return PurchaseItemDto.builder()
            .id(id)
            .purchaseId(purchaseId)
            .productId(productId)
            .quantity(quantity)
            .unitPrice(unitPrice)
            .totalPrice(totalPrice)
            .currency(currency)
            .metadata(metadata)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_purchase_items")
    .column("id", UUID.class)
    .column("purchaseId", UUID.class)
    .column("productId", UUID.class)
    .column("quantity", Integer.class)
    .column("unitPrice", BigDecimal.class)
    .column("totalPrice", BigDecimal.class)
    .column("currency", String.class, 3)
    .column("metadata", Map.class)
    .column("createdAt", Instant.class)
    .column("updatedAt", Instant.class)
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
