package com.gossamercms.products.data;

import com.gossamercms.products.dtos.PurchaseDto;
import com.gossamercms.products.models.Purchase;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.mvc.data.ListResultset;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class PurchasesDbService extends BaseDbService<Purchase, PurchaseDto> {

    public PurchasesDbService(DataSourceManager ds) {
        super(Purchase.class, PurchaseDto.class, ds);
    }

    @Override
    protected Purchase mapToEntity(PurchaseDto dto) {
        return dto.toEntity();
    }

    @Override
    protected PurchaseDto mapToDto(Purchase entity) {
        return entity.toDto();
    }

    @Override
    protected PurchaseDto removeExcludedFields(PurchaseDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

}
