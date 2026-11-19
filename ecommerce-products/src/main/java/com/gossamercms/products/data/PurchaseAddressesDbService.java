package com.gossamercms.products.data;

import com.gossamercms.products.dtos.PurchaseAddressDto;
import com.gossamercms.products.models.PurchaseAddress;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;


@ModuleDbService
public class PurchaseAddressesDbService extends BaseDbService<PurchaseAddress, PurchaseAddressDto> {

    public PurchaseAddressesDbService(DataSourceManager ds) {
        super(PurchaseAddress.class, PurchaseAddressDto.class, ds);
    }

    @Override
    protected PurchaseAddress mapToEntity(PurchaseAddressDto dto) {
        return dto.toEntity();
    }

    @Override
    protected PurchaseAddressDto mapToDto(PurchaseAddress entity) {
        return entity.toDto();
    }

    @Override
    protected PurchaseAddressDto removeExcludedFields(PurchaseAddressDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

}
