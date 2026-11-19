package com.gossamercms.products.dtos;

import com.gossamercms.products.models.PurchaseFulfillment;
import com.gossamercms.mvc.data.DtoWithId;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseFulfillmentDto implements DtoWithId{
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

    public PurchaseFulfillment toEntity() {
        return PurchaseFulfillment.builder()
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
}
