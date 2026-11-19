package com.gossamercms.products.dtos;

import com.gossamercms.products.models.PurchaseFulfillmentItem;
import com.gossamercms.mvc.data.DtoWithId;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseFulfillmentItemDto implements DtoWithId{
    private UUID id;
    private UUID fulfillmentId;
    private UUID purchaseItemId;
    private BigDecimal quantity;
    private Instant createdAt;

    public PurchaseFulfillmentItem toEntity() {
        return PurchaseFulfillmentItem.builder()
            .id(id)
            .fulfillmentId(fulfillmentId)
            .purchaseItemId(purchaseItemId)
            .quantity(quantity)
            .createdAt(createdAt)
            .build();
    }
}
