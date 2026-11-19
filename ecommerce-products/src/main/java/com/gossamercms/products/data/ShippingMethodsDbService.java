package com.gossamercms.products.data;

import com.gossamercms.products.dtos.ShippingMethodDto;
import com.gossamercms.products.models.ShippingMethod;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.mvc.data.ListResultset;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class ShippingMethodsDbService extends BaseDbService<ShippingMethod, ShippingMethodDto> {

    public ShippingMethodsDbService(DataSourceManager ds) {
        super(ShippingMethod.class, ShippingMethodDto.class, ds);
    }

    @Override
    protected ShippingMethod mapToEntity(ShippingMethodDto dto) {
        return dto.toEntity();
    }

    @Override
    protected ShippingMethodDto mapToDto(ShippingMethod entity) {
        return entity.toDto();
    }

    @Override
    protected ShippingMethodDto removeExcludedFields(ShippingMethodDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

}
