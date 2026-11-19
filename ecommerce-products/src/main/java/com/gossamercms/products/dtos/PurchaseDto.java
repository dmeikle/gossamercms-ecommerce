package com.gossamercms.products.dtos;

import com.gossamercms.mvc.helpers.annotations.JsonColumn;
import com.gossamercms.products.models.Purchase;
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
public class PurchaseDto implements DtoWithId{
    private UUID id;
    private String purchaseNumber;
    private UUID customerContextId;
    private String status;
    private BigDecimal totalAmount;
    private String currency;

    private BigDecimal subtotal;
    private BigDecimal discountTotal;
    private BigDecimal shippingTotal;
    private BigDecimal taxTotal;

    @JsonColumn
    private Map<String, Object> metadata;
    private UUID createdBy;
    private Instant createdAt;
    private UUID updatedBy;
    private Instant updatedAt;
    private UUID deletedBy;
    private Instant deletedAt;

    public Purchase toEntity() {
        return Purchase.builder()
            .id(id)
            .purchaseNumber(purchaseNumber)
            .customerContextId(customerContextId)
            .status(status)
            .subtotal(subtotal)
            .discountTotal(discountTotal)
            .shippingTotal(shippingTotal)
            .taxTotal(taxTotal)
            .totalAmount(totalAmount)
            .currency(currency)
            .metadata(metadata)
            .createdBy(createdBy)
            .createdAt(createdAt)
            .updatedBy(updatedBy)
            .updatedAt(updatedAt)
            .deletedBy(deletedBy)
            .deletedAt(deletedAt)
            .build();
    }
}
