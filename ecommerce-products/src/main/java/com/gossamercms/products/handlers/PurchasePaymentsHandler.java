package com.gossamercms.products.handlers;

import com.gossamercms.products.data.PurchasePaymentsDbService;
import com.gossamercms.products.dtos.PurchasePaymentDto;
import com.gossamercms.products.models.PurchasePayment;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class PurchasePaymentsHandler extends BaseHandler<PurchasePayment, PurchasePaymentDto> {

    public PurchasePaymentsHandler(PurchasePaymentsDbService db) {
        super(db);
    }
}
