package com.gossamercms.products.handlers;

import com.gossamercms.products.data.PurchaseFulfillmentsDbService;
import com.gossamercms.products.dtos.PurchaseFulfillmentDto;
import com.gossamercms.products.models.PurchaseFulfillment;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class PurchaseFulfillmentsHandler extends BaseHandler<PurchaseFulfillment, PurchaseFulfillmentDto> {

    public PurchaseFulfillmentsHandler(PurchaseFulfillmentsDbService db) {
        super(db);
    }
}
