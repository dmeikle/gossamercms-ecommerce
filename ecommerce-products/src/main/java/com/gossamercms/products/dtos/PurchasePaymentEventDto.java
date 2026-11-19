package com.gossamercms.products.dtos;

import com.gossamercms.products.models.PurchasePaymentEvent;
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
public class PurchasePaymentEventDto implements DtoWithId{
    private UUID id;
    private UUID paymentId;
    private String provider;
    private String providerEventId;
    private String eventType;
    private String processingStatus;
    private Map<String, Object> payload;
    private Instant processedAt;
    private String errorMessage;
    private Instant createdAt;

    public PurchasePaymentEvent toEntity() {
        return PurchasePaymentEvent.builder()
            .id(id)
            .paymentId(paymentId)
            .provider(provider)
            .providerEventId(providerEventId)
            .eventType(eventType)
            .processingStatus(processingStatus)
            .payload(payload)
            .processedAt(processedAt)
            .errorMessage(errorMessage)
            .createdAt(createdAt)
            .build();
    }
}
