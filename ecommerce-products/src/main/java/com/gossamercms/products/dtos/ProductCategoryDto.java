package com.gossamercms.products.dtos;

import com.gossamercms.products.models.ProductCategory;
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
public class ProductCategoryDto implements DtoWithId{
    private UUID id;
    private UUID productId;
    private UUID categoryId;

    public ProductCategory toEntity() {
        return ProductCategory.builder()
            .id(id)
            .productId(productId)
            .categoryId(categoryId)
            .build();
    }
}
