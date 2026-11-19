package com.gossamercms.products.converters;

import com.gossamercms.products.data.PurchasePaymentsDbService;
import com.gossamercms.products.dtos.PurchasePaymentDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class PurchasePaymentConverter
        extends BaseConverter<PurchasePaymentDto>
         implements Converter<String, PurchasePaymentDto> {

    public PurchasePaymentConverter(PurchasePaymentsDbService dbService) {
        super(dbService, PurchasePaymentDto.class);
    }
}

