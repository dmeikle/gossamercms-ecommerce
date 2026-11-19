package com.gossamercms.products.dtos;

import com.gossamercms.products.models.ShippingMethod;
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
public class ShippingMethodDto implements DtoWithId{
    private UUID id;
    private String name;
    private String description;
    private String methodType;
    private BigDecimal price;
    private String currency;
    private Boolean isActive;
    private Map<String, Object> metadata;
    private Instant createdAt;
    private Instant updatedAt;

    public ShippingMethod toEntity() {
        return ShippingMethod.builder()
            .id(id)
            .name(name)
            .description(description)
            .methodType(methodType)
            .price(price)
            .currency(currency)
            .isActive(isActive)
            .metadata(metadata)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }
}
