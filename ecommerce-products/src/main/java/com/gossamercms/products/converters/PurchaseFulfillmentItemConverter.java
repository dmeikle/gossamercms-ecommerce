package com.gossamercms.products.converters;

import com.gossamercms.products.data.PurchaseFulfillmentItemsDbService;
import com.gossamercms.products.dtos.PurchaseFulfillmentItemDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class PurchaseFulfillmentItemConverter
        extends BaseConverter<PurchaseFulfillmentItemDto>
         implements Converter<String, PurchaseFulfillmentItemDto> {

    public PurchaseFulfillmentItemConverter(PurchaseFulfillmentItemsDbService dbService) {
        super(dbService, PurchaseFulfillmentItemDto.class);
    }
}

