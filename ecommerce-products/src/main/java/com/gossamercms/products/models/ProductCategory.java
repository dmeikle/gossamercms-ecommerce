package com.gossamercms.products.models;

import com.gossamercms.products.dtos.ProductCategoryDto;
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
public class ProductCategory implements BaseModel{
    private UUID id;
    private UUID productId;
    private UUID categoryId;

    public ProductCategoryDto toDto() {
        return ProductCategoryDto.builder()
            .id(id)
            .productId(productId)
            .categoryId(categoryId)
            .build();
    }


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_product_categories")
    .column("id", UUID.class)
    .column("productId", UUID.class)
    .column("categoryId", UUID.class)
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
