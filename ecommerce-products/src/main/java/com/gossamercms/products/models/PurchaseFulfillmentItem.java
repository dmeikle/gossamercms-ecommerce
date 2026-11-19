package com.gossamercms.products.models;

import com.gossamercms.products.dtos.PurchaseFulfillmentItemDto;
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
public class PurchaseFulfillmentItem implements BaseModel{
    private UUID id;
    private UUID fulfillmentId;
    private UUID purchaseItemId;
    private BigDecimal quantity;
    private Instant createdAt;

    public PurchaseFulfillmentItemDto toDto() {
        return PurchaseFulfillmentItemDto.builder()
            .id(id)
            .fulfillmentId(fulfillmentId)
            .purchaseItemId(purchaseItemId)
            .quantity(quantity)
            .createdAt(createdAt)
            .build();
    }


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_purchase_fulfillment_items")
    .column("id", UUID.class)
    .column("fulfillmentId", UUID.class)
    .column("purchaseItemId", UUID.class)
    .column("quantity", BigDecimal.class)
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
