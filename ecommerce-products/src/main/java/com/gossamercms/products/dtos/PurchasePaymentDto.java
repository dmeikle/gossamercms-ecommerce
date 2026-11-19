package com.gossamercms.products.dtos;

import com.gossamercms.products.models.PurchasePayment;
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
public class PurchasePaymentDto implements DtoWithId{
    private UUID id;
    private UUID purchaseId;
    private String provider;
    private String paymentMethod;
    private String providerPaymentId;
    private String providerCustomerId;
    private BigDecimal amount;
    private String currency;
    private String providerCheckoutId;
    private String status;
    private String failureCode;
    private String failureMessage;
    private Instant paidAt;
    private String idempotencyKey;
    private Map<String, Object> metadata;
    private Instant createdAt;
    private Instant updatedAt;

    public PurchasePayment toEntity() {
        return PurchasePayment.builder()
            .id(id)
            .purchaseId(purchaseId)
            .provider(provider)
            .paymentMethod(paymentMethod)
            .providerPaymentId(providerPaymentId)
            .providerCustomerId(providerCustomerId)
            .amount(amount)
            .providerCheckoutId(providerCheckoutId)
            .currency(currency)
            .status(status)
            .failureCode(failureCode)
            .failureMessage(failureMessage)
            .paidAt(paidAt)
            .idempotencyKey(idempotencyKey)
            .metadata(metadata)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }
}
