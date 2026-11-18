package com.gossamercms.products.dtos;

import com.gossamercms.products.models.CartItem;
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
public class CartItemDto implements DtoWithId{
    private UUID id;
    private UUID cartId;
    private UUID productId;
    private Integer quantity;
    private Instant createdAt;
    private Instant updatedAt;

    public CartItem toEntity() {
        return CartItem.builder()
            .id(id)
            .cartId(cartId)
            .productId(productId)
            .quantity(quantity)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }
}
