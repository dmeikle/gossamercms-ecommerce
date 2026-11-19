package com.gossamercms.products.models;

import com.gossamercms.products.dtos.PurchaseFulfillmentDto;
import com.gossamercms.mvc.models.BaseModel;
import com.gossamercms.mvc.models.ModelMeta;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;
import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseFulfillment implements BaseModel{
    private UUID id;
    private UUID purchaseId;
    private UUID shippingMethodId;
    private String fulfillmentType;
    private String status;
    private String trackingNumber;
    private String trackingUrl;
    private String carrier;
    private String pickupReference;
    private Instant shippedAt;
    private Instant deliveredAt;
    private Instant collectedAt;
    private Instant completedAt;
    private Map<String, Object> metadata;
    private Instant createdAt;
    private Instant updatedAt;

    public PurchaseFulfillmentDto toDto() {
        return PurchaseFulfillmentDto.builder()
            .id(id)
            .purchaseId(purchaseId)
            .shippingMethodId(shippingMethodId)
            .fulfillmentType(fulfillmentType)
            .status(status)
            .trackingNumber(trackingNumber)
            .trackingUrl(trackingUrl)
            .carrier(carrier)
            .pickupReference(pickupReference)
            .shippedAt(shippedAt)
            .deliveredAt(deliveredAt)
            .collectedAt(collectedAt)
            .completedAt(completedAt)
            .metadata(metadata)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_purchase_fulfillments")
    .column("id", UUID.class)
    .column("purchaseId", UUID.class)
    .column("shippingMethodId", UUID.class)
    .column("fulfillmentType", String.class, 20)
    .column("status", String.class, 30)
    .column("trackingNumber", String.class, 150)
    .column("trackingUrl", String.class, 1000)
    .column("carrier", String.class, 100)
    .column("pickupReference", String.class, 100)
    .column("shippedAt", Instant.class)
    .column("deliveredAt", Instant.class)
    .column("collectedAt", Instant.class)
    .column("completedAt", Instant.class)
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
