package com.gossamercms.products.converters;

import com.gossamercms.products.data.PurchasesDbService;
import com.gossamercms.products.dtos.PurchaseDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class PurchaseConverter
        extends BaseConverter<PurchaseDto>
         implements Converter<String, PurchaseDto> {

    public PurchaseConverter(PurchasesDbService dbService) {
        super(dbService, PurchaseDto.class);
    }
}

