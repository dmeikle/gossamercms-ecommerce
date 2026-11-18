package com.gossamercms.products.data;

import com.gossamercms.products.dtos.PurchaseItemDto;
import com.gossamercms.products.models.PurchaseItem;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.mvc.data.ListResultset;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class PurchaseItemsDbService extends BaseDbService<PurchaseItem, PurchaseItemDto> {

    public PurchaseItemsDbService(DataSourceManager ds) {
        super(PurchaseItem.class, PurchaseItemDto.class, ds);
    }

    @Override
    protected PurchaseItem mapToEntity(PurchaseItemDto dto) {
        return dto.toEntity();
    }

    @Override
    protected PurchaseItemDto mapToDto(PurchaseItem entity) {
        return entity.toDto();
    }

    @Override
    protected PurchaseItemDto removeExcludedFields(PurchaseItemDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

}
