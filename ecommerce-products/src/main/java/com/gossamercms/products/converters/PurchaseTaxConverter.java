package com.gossamercms.products.converters;

import com.gossamercms.products.data.PurchaseTaxsDbService;
import com.gossamercms.products.dtos.PurchaseTaxDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class PurchaseTaxConverter
        extends BaseConverter<PurchaseTaxDto>
         implements Converter<String, PurchaseTaxDto> {

    public PurchaseTaxConverter(PurchaseTaxsDbService dbService) {
        super(dbService, PurchaseTaxDto.class);
    }
}

