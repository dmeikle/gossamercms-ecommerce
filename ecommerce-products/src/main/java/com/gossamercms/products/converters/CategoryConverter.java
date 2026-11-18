package com.gossamercms.products.converters;

import com.gossamercms.products.data.CategorysDbService;
import com.gossamercms.products.dtos.CategoryDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class CategoryConverter
        extends BaseConverter<CategoryDto>
         implements Converter<String, CategoryDto> {

    public CategoryConverter(CategorysDbService dbService) {
        super(dbService, CategoryDto.class);
    }
}

