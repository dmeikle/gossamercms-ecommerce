package com.gossamercms.products.models;

import com.gossamercms.products.dtos.ShippingMethodDto;
import com.gossamercms.mvc.models.BaseModel;
import com.gossamercms.mvc.models.ModelMeta;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingMethod implements BaseModel{
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

    public ShippingMethodDto toDto() {
        return ShippingMethodDto.builder()
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


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_shipping_methods")
    .column("id", UUID.class)
    .column("name", String.class, 150)
    .column("description", String.class)
    .column("methodType", String.class, 20)
    .column("price", BigDecimal.class)
    .column("currency", String.class, 3)
    .column("isActive", Boolean.class)
    .column("metadata", Map.class)
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
