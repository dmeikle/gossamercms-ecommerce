package com.gossamercms.products.models;

import com.gossamercms.products.dtos.PurchaseRefundDto;
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
public class PurchaseRefund implements BaseModel{
    private UUID id;
    private UUID purchaseId;
    private UUID paymentId;
    private String providerRefundId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String reason;
    private String description;
    private String provider;
    private String failureCode;
    private String failureMessage;
    private Instant refundedAt;
    private UUID requestedBy;
    private Map<String, Object> metadata;
    private Instant createdAt;
    private Instant updatedAt;
    private UUID createdBy;
    private UUID updatedBy;

    public PurchaseRefundDto toDto() {
        return PurchaseRefundDto.builder()
            .id(id)
            .purchaseId(purchaseId)
            .paymentId(paymentId)
            .providerRefundId(providerRefundId)
            .amount(amount)
            .currency(currency)
            .status(status)
            .reason(reason)
            .description(description)
            .provider(provider)
            .failureCode(failureCode)
            .failureMessage(failureMessage)
            .refundedAt(refundedAt)
            .requestedBy(requestedBy)
            .metadata(metadata)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .createdBy(createdBy)
            .updatedBy(updatedBy)
            .build();
    }


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_purchase_refunds")
    .column("id", UUID.class)
    .column("purchaseId", UUID.class)
    .column("paymentId", UUID.class)
    .column("providerRefundId", String.class, 255)
    .column("amount", BigDecimal.class)
    .column("currency", String.class, 3)
    .column("status", String.class, 30)
    .column("reason", String.class, 50)
    .column("description", String.class)
    .column("provider", String.class, 50)
    .column("failureCode", String.class, 100)
    .column("failureMessage", String.class)
    .column("refundedAt", Instant.class)
    .column("requestedBy", UUID.class)
    .column("metadata", Map.class)
    .column("createdAt", Instant.class)
    .column("updatedAt", Instant.class)
    .column("createdBy", UUID.class)
    .column("updatedBy", UUID.class)
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
