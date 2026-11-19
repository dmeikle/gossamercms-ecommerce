package com.gossamercms.products.converters;

import com.gossamercms.products.data.ShippingMethodsDbService;
import com.gossamercms.products.dtos.ShippingMethodDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class ShippingMethodConverter
        extends BaseConverter<ShippingMethodDto>
         implements Converter<String, ShippingMethodDto> {

    public ShippingMethodConverter(ShippingMethodsDbService dbService) {
        super(dbService, ShippingMethodDto.class);
    }
}

