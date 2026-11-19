package com.gossamercms.products.converters;

import com.gossamercms.products.data.PurchaseFulfillmentsDbService;
import com.gossamercms.products.dtos.PurchaseFulfillmentDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class PurchaseFulfillmentConverter
        extends BaseConverter<PurchaseFulfillmentDto>
         implements Converter<String, PurchaseFulfillmentDto> {

    public PurchaseFulfillmentConverter(PurchaseFulfillmentsDbService dbService) {
        super(dbService, PurchaseFulfillmentDto.class);
    }
}

