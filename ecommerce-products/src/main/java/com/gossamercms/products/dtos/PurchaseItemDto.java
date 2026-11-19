package com.gossamercms.products.dtos;

import com.gossamercms.mvc.helpers.annotations.JsonColumn;
import com.gossamercms.products.models.PurchaseItem;
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
public class PurchaseItemDto implements DtoWithId{
    private UUID id;
    private UUID purchaseId;
    private UUID productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String currency;
    @JsonColumn
    private Map<String, Object> metadata;
    private Instant createdAt;
    private Instant updatedAt;

    public PurchaseItem toEntity() {
        return PurchaseItem.builder()
            .id(id)
            .purchaseId(purchaseId)
            .productId(productId)
            .productName(productName)
            .quantity(quantity)
            .unitPrice(unitPrice)
            .totalPrice(totalPrice)
            .currency(currency)
            .metadata(metadata)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }
}
