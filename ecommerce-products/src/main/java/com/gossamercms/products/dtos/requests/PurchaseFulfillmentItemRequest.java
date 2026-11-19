package com.gossamercms.products.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseFulfillmentItemRequest {
    private UUID fulfillmentId;
    private UUID purchaseItemId;
    private BigDecimal quantity;

}
