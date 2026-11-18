package com.gossamercms.products.data;

import com.gossamercms.products.dtos.ProductCategoryDto;
import com.gossamercms.products.models.ProductCategory;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.mvc.data.ListResultset;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class ProductCategorysDbService extends BaseDbService<ProductCategory, ProductCategoryDto> {

    public ProductCategorysDbService(DataSourceManager ds) {
        super(ProductCategory.class, ProductCategoryDto.class, ds);
    }

    @Override
    protected ProductCategory mapToEntity(ProductCategoryDto dto) {
        return dto.toEntity();
    }

    @Override
    protected ProductCategoryDto mapToDto(ProductCategory entity) {
        return entity.toDto();
    }

    @Override
    protected ProductCategoryDto removeExcludedFields(ProductCategoryDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

}
