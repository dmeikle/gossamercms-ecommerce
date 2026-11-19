package com.gossamercms.products.converters;

import com.gossamercms.products.data.PurchasePaymentEventsDbService;
import com.gossamercms.products.dtos.PurchasePaymentEventDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class PurchasePaymentEventConverter
        extends BaseConverter<PurchasePaymentEventDto>
         implements Converter<String, PurchasePaymentEventDto> {

    public PurchasePaymentEventConverter(PurchasePaymentEventsDbService dbService) {
        super(dbService, PurchasePaymentEventDto.class);
    }
}

