package com.gossamercms.products.converters;

import com.gossamercms.products.data.ProductsDbService;
import com.gossamercms.products.dtos.ProductDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class ProductConverter
        extends BaseConverter<ProductDto>
         implements Converter<String, ProductDto> {

    public ProductConverter(ProductsDbService dbService) {
        super(dbService, ProductDto.class);
    }
}

