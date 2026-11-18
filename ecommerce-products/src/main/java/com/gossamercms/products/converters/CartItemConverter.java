package com.gossamercms.products.converters;

import com.gossamercms.products.data.CartItemsDbService;
import com.gossamercms.products.dtos.CartItemDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class CartItemConverter
        extends BaseConverter<CartItemDto>
         implements Converter<String, CartItemDto> {

    public CartItemConverter(CartItemsDbService dbService) {
        super(dbService, CartItemDto.class);
    }
}

