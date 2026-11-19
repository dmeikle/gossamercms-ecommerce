package com.gossamercms.products.models;

import com.gossamercms.mvc.helpers.annotations.JsonColumn;
import com.gossamercms.products.dtos.ProductDto;
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
public class Product implements BaseModel{
    private UUID id;
    private String sku;
    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private String currency;
    private String status;
    private String taxCode;
    @JsonColumn
    private Map<String, Object> metadata;
    private String thumbnailUrl;
    private Instant createdAt;
    private Instant updatedAt;
    private UUID createdBy;
    private UUID updatedBy;

    public ProductDto toDto() {
        return ProductDto.builder()
            .id(id)
            .sku(sku)
            .name(name)
            .slug(slug)
            .description(description)
            .price(price)
            .currency(currency)
            .status(status)
            .taxCode(taxCode)
            .metadata(metadata)
            .thumbnailUrl(thumbnailUrl)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .createdBy(createdBy)
            .updatedBy(updatedBy)
            .build();
    }


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_products")
    .column("id", UUID.class)
    .column("sku", String.class, 50)
    .column("name", String.class, 255)
    .column("slug", String.class, 255)
    .column("description", String.class)
    .column("price", BigDecimal.class)
    .column("currency", String.class, 3)
    .column("status", String.class, 20)
    .column("taxCode", String.class, 50)
    .column("metadata", Map.class)
    .column("thumbnailUrl", String.class)
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
