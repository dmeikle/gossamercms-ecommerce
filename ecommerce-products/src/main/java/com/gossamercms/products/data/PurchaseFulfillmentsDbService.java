package com.gossamercms.products.data;

import com.gossamercms.products.dtos.PurchaseFulfillmentDto;
import com.gossamercms.products.models.PurchaseFulfillment;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.mvc.data.ListResultset;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class PurchaseFulfillmentsDbService extends BaseDbService<PurchaseFulfillment, PurchaseFulfillmentDto> {

    public PurchaseFulfillmentsDbService(DataSourceManager ds) {
        super(PurchaseFulfillment.class, PurchaseFulfillmentDto.class, ds);
    }

    @Override
    protected PurchaseFulfillment mapToEntity(PurchaseFulfillmentDto dto) {
        return dto.toEntity();
    }

    @Override
    protected PurchaseFulfillmentDto mapToDto(PurchaseFulfillment entity) {
        return entity.toDto();
    }

    @Override
    protected PurchaseFulfillmentDto removeExcludedFields(PurchaseFulfillmentDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

}
