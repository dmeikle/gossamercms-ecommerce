package com.gossamercms.products.dtos;

import com.gossamercms.products.models.Cart;
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
public class CartDto implements DtoWithId{
    private UUID id;
    private UUID userContextId;
    private String sessionId;
    private String status;
    private Instant expiresAt;
    private Instant createdAt;
    private Instant updatedAt;
    private UUID createdBy;
    private UUID updatedBy;

    public Cart toEntity() {
        return Cart.builder()
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
}
