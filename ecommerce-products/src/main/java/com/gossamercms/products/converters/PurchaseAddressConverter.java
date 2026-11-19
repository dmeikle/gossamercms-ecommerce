package com.gossamercms.products.converters;

import com.gossamercms.products.data.PurchaseAddressesDbService;
import com.gossamercms.products.dtos.PurchaseAddressDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;


@ModuleConverter
public class PurchaseAddressConverter
        extends BaseConverter<PurchaseAddressDto>
         implements Converter<String, PurchaseAddressDto> {

    public PurchaseAddressConverter(PurchaseAddressesDbService dbService) {
        super(dbService, PurchaseAddressDto.class);
    }
}

