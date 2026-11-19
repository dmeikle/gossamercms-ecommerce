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
public class PurchaseFulfillmentListResponse {
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

}