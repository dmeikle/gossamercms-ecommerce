package com.gossamercms.products.converters;

import com.gossamercms.products.data.CartsDbService;
import com.gossamercms.products.dtos.CartDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class CartConverter
        extends BaseConverter<CartDto>
         implements Converter<String, CartDto> {

    public CartConverter(CartsDbService dbService) {
        super(dbService, CartDto.class);
    }
}

