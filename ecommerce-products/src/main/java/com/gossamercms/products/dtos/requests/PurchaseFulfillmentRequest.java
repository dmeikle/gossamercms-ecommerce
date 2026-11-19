package com.gossamercms.products.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseFulfillmentRequest {
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

}
