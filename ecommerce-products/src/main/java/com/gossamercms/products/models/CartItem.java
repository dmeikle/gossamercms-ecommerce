package com.gossamercms.products.models;

import com.gossamercms.mvc.models.BaseModel;
import com.gossamercms.mvc.models.ModelMeta;
import com.gossamercms.products.dtos.CartItemDto;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements BaseModel{
    private UUID id;
    private UUID cartId;
    private UUID productId;
    private Integer quantity;
    private Instant createdAt;
    private Instant updatedAt;

    public CartItemDto toDto() {
        return CartItemDto.builder()
            .id(id)
            .cartId(cartId)
            .productId(productId)
            .quantity(quantity)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_cart_items")
    .column("id", UUID.class)
    .column("cartId", UUID.class)
    .column("productId", UUID.class)
    .column("quantity", Integer.class)
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
