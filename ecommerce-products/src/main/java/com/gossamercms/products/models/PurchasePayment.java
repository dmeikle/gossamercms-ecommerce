package com.gossamercms.products.models;

import com.gossamercms.products.dtos.PurchasePaymentDto;
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
public class PurchasePayment implements BaseModel{
    private UUID id;
    private UUID purchaseId;
    private String provider;
    private String paymentMethod;
    private String providerPaymentId;
    private String providerCustomerId;
    private BigDecimal amount;
    private String providerCheckoutId;
    private String currency;
    private String status;
    private String failureCode;
    private String failureMessage;
    private Instant paidAt;
    private String idempotencyKey;
    private Map<String, Object> metadata;
    private Instant createdAt;
    private Instant updatedAt;

    public PurchasePaymentDto toDto() {
        return PurchasePaymentDto.builder()
            .id(id)
            .purchaseId(purchaseId)
            .provider(provider)
            .paymentMethod(paymentMethod)
            .providerPaymentId(providerPaymentId)
            .providerCustomerId(providerCustomerId)
            .amount(amount)
            .currency(currency)
            .status(status)
            .providerCheckoutId(providerCheckoutId)
            .failureCode(failureCode)
            .failureMessage(failureMessage)
            .paidAt(paidAt)
            .idempotencyKey(idempotencyKey)
            .metadata(metadata)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_purchase_payments")
    .column("id", UUID.class)
    .column("purchaseId", UUID.class)
    .column("provider", String.class, 50)
    .column("paymentMethod", String.class, 50)
    .column("providerPaymentId", String.class, 255)
    .column("providerCustomerId", String.class, 255)
    .column("amount", BigDecimal.class)
    .column("currency", String.class, 3)
    .column("status", String.class, 30)
    .column("providerCheckoutId", String.class, 255)
    .column("failureCode", String.class, 100)
    .column("failureMessage", String.class)
    .column("paidAt", Instant.class)
    .column("idempotencyKey", String.class, 255)
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
