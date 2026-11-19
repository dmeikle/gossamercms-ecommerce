package com.gossamercms.products.handlers;

import com.gossamercms.products.data.PurchaseRefundsDbService;
import com.gossamercms.products.dtos.PurchaseRefundDto;
import com.gossamercms.products.models.PurchaseRefund;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class PurchaseRefundsHandler extends BaseHandler<PurchaseRefund, PurchaseRefundDto> {

    public PurchaseRefundsHandler(PurchaseRefundsDbService db) {
        super(db);
    }
}
