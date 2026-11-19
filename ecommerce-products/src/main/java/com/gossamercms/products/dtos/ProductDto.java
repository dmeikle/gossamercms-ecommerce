package com.gossamercms.products.dtos;

import com.gossamercms.mvc.helpers.annotations.JsonColumn;
import com.gossamercms.products.models.Product;
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
public class ProductDto implements DtoWithId{
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

    public Product toEntity() {
        return Product.builder()
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
}
