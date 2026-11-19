package com.gossamercms.products.models;

import com.gossamercms.products.dtos.PurchasePaymentEventDto;
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
public class PurchasePaymentEvent implements BaseModel{
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

    public PurchasePaymentEventDto toDto() {
        return PurchasePaymentEventDto.builder()
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


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_purchase_payment_events")
    .column("id", UUID.class)
    .column("paymentId", UUID.class)
    .column("provider", String.class, 50)
    .column("providerEventId", String.class, 255)
    .column("eventType", String.class, 150)
    .column("processingStatus", String.class, 20)
    .column("payload", Map.class)
    .column("processedAt", Instant.class)
    .column("errorMessage", String.class)
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
