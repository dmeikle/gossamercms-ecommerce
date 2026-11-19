package com.gossamercms.products.data;

import com.gossamercms.products.dtos.PurchaseFulfillmentItemDto;
import com.gossamercms.products.models.PurchaseFulfillmentItem;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.mvc.data.ListResultset;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class PurchaseFulfillmentItemsDbService extends BaseDbService<PurchaseFulfillmentItem, PurchaseFulfillmentItemDto> {

    public PurchaseFulfillmentItemsDbService(DataSourceManager ds) {
        super(PurchaseFulfillmentItem.class, PurchaseFulfillmentItemDto.class, ds);
    }

    @Override
    protected PurchaseFulfillmentItem mapToEntity(PurchaseFulfillmentItemDto dto) {
        return dto.toEntity();
    }

    @Override
    protected PurchaseFulfillmentItemDto mapToDto(PurchaseFulfillmentItem entity) {
        return entity.toDto();
    }

    @Override
    protected PurchaseFulfillmentItemDto removeExcludedFields(PurchaseFulfillmentItemDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

}
