package com.gossamercms.products.converters;

import com.gossamercms.products.data.ProductCategorysDbService;
import com.gossamercms.products.dtos.ProductCategoryDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class ProductCategoryConverter
        extends BaseConverter<ProductCategoryDto>
         implements Converter<String, ProductCategoryDto> {

    public ProductCategoryConverter(ProductCategorysDbService dbService) {
        super(dbService, ProductCategoryDto.class);
    }
}

