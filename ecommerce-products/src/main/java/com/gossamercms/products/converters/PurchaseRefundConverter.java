package com.gossamercms.products.converters;

import com.gossamercms.products.data.PurchaseRefundsDbService;
import com.gossamercms.products.dtos.PurchaseRefundDto;
import com.gossamercms.mvc.annotations.ModuleConverter;
import org.springframework.core.convert.converter.Converter;
import com.gossamercms.mvc.converters.BaseConverter;

import java.util.UUID;


@ModuleConverter
public class PurchaseRefundConverter
        extends BaseConverter<PurchaseRefundDto>
         implements Converter<String, PurchaseRefundDto> {

    public PurchaseRefundConverter(PurchaseRefundsDbService dbService) {
        super(dbService, PurchaseRefundDto.class);
    }
}

