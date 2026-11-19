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
public class PurchasePaymentEventResponse {
    private UUID paymentId;
    private String provider;
    private String providerEventId;
    private String eventType;
    private String processingStatus;
    private Map<String, Object> payload;
    private Instant processedAt;
    private String errorMessage;
    private Instant createdAt;

}