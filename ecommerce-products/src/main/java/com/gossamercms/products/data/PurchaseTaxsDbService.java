package com.gossamercms.products.data;

import com.gossamercms.products.dtos.PurchaseTaxDto;
import com.gossamercms.products.models.PurchaseTax;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.mvc.data.ListResultset;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class PurchaseTaxsDbService extends BaseDbService<PurchaseTax, PurchaseTaxDto> {

    public PurchaseTaxsDbService(DataSourceManager ds) {
        super(PurchaseTax.class, PurchaseTaxDto.class, ds);
    }

    @Override
    protected PurchaseTax mapToEntity(PurchaseTaxDto dto) {
        return dto.toEntity();
    }

    @Override
    protected PurchaseTaxDto mapToDto(PurchaseTax entity) {
        return entity.toDto();
    }

    @Override
    protected PurchaseTaxDto removeExcludedFields(PurchaseTaxDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

}
