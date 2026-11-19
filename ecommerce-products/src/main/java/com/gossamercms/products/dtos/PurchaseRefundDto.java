package com.gossamercms.products.dtos;

import com.gossamercms.products.models.PurchaseRefund;
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
public class PurchaseRefundDto implements DtoWithId{
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

    public PurchaseRefund toEntity() {
        return PurchaseRefund.builder()
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
}
