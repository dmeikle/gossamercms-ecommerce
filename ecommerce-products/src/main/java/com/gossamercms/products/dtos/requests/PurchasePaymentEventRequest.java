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
public class PurchasePaymentEventRequest {
    private UUID paymentId;
    private String provider;
    private String providerEventId;
    private String eventType;
    private String processingStatus;
    private Map<String, Object> payload;
    private Instant processedAt;
    private String errorMessage;

}
