package com.gossamercms.products.models;

import com.gossamercms.products.dtos.CartDto;
import com.gossamercms.mvc.models.BaseModel;
import com.gossamercms.mvc.models.ModelMeta;
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
public class Cart implements BaseModel{
    private UUID id;
    private UUID userContextId;
    private String sessionId;
    private String status;
    private Instant expiresAt;
    private Instant createdAt;
    private Instant updatedAt;
    private UUID createdBy;
    private UUID updatedBy;

    public CartDto toDto() {
        return CartDto.builder()
            .id(id)
            .userContextId(userContextId)
            .sessionId(sessionId)
            .status(status)
            .expiresAt(expiresAt)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .createdBy(createdBy)
            .updatedBy(updatedBy)
            .build();
    }


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_carts")
    .column("id", UUID.class)
    .column("userContextId", UUID.class)
    .column("sessionId", String.class, 100)
    .column("status", String.class, 20)
    .column("expiresAt", Instant.class)
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
