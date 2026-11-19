package com.gossamercms.products.data;

import com.gossamercms.products.dtos.PurchasePaymentEventDto;
import com.gossamercms.products.models.PurchasePaymentEvent;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.mvc.data.ListResultset;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class PurchasePaymentEventsDbService extends BaseDbService<PurchasePaymentEvent, PurchasePaymentEventDto> {

    public PurchasePaymentEventsDbService(DataSourceManager ds) {
        super(PurchasePaymentEvent.class, PurchasePaymentEventDto.class, ds);
    }

    @Override
    protected PurchasePaymentEvent mapToEntity(PurchasePaymentEventDto dto) {
        return dto.toEntity();
    }

    @Override
    protected PurchasePaymentEventDto mapToDto(PurchasePaymentEvent entity) {
        return entity.toDto();
    }

    @Override
    protected PurchasePaymentEventDto removeExcludedFields(PurchasePaymentEventDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

}
