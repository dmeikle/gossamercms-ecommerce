package com.gossamercms.products.converters;

import com.gossamercms.products.data.PurchaseItemsDbService;
import com.gossamercms.products.dtos.PurchaseItemDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class PurchaseItemConverter
        extends BaseConverter<PurchaseItemDto>
         implements Converter<String, PurchaseItemDto> {

    public PurchaseItemConverter(PurchaseItemsDbService dbService) {
        super(dbService, PurchaseItemDto.class);
    }
}

