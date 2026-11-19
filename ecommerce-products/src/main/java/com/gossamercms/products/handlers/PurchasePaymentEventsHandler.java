package com.gossamercms.products.handlers;

import com.gossamercms.products.data.PurchasePaymentEventsDbService;
import com.gossamercms.products.dtos.PurchasePaymentEventDto;
import com.gossamercms.products.models.PurchasePaymentEvent;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class PurchasePaymentEventsHandler extends BaseHandler<PurchasePaymentEvent, PurchasePaymentEventDto> {

    public PurchasePaymentEventsHandler(PurchasePaymentEventsDbService db) {
        super(db);
    }
}
