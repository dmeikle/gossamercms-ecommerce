package com.gossamercms.products.data;

import com.gossamercms.products.dtos.PurchaseRefundDto;
import com.gossamercms.products.models.PurchaseRefund;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.mvc.data.ListResultset;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class PurchaseRefundsDbService extends BaseDbService<PurchaseRefund, PurchaseRefundDto> {

    public PurchaseRefundsDbService(DataSourceManager ds) {
        super(PurchaseRefund.class, PurchaseRefundDto.class, ds);
    }

    @Override
    protected PurchaseRefund mapToEntity(PurchaseRefundDto dto) {
        return dto.toEntity();
    }

    @Override
    protected PurchaseRefundDto mapToDto(PurchaseRefund entity) {
        return entity.toDto();
    }

    @Override
    protected PurchaseRefundDto removeExcludedFields(PurchaseRefundDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

}
