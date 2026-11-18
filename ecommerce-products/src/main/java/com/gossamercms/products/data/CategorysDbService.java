package com.gossamercms.products.data;

import com.gossamercms.products.dtos.CategoryDto;
import com.gossamercms.products.models.Category;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.mvc.data.ListResultset;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class CategorysDbService extends BaseDbService<Category, CategoryDto> {

    public CategorysDbService(DataSourceManager ds) {
        super(Category.class, CategoryDto.class, ds);
    }

    @Override
    protected Category mapToEntity(CategoryDto dto) {
        return dto.toEntity();
    }

    @Override
    protected CategoryDto mapToDto(Category entity) {
        return entity.toDto();
    }

    @Override
    protected CategoryDto removeExcludedFields(CategoryDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

}
