package com.gossamercms.products.handlers;

import com.gossamercms.products.data.PurchaseFulfillmentItemsDbService;
import com.gossamercms.products.dtos.PurchaseFulfillmentItemDto;
import com.gossamercms.products.models.PurchaseFulfillmentItem;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class PurchaseFulfillmentItemsHandler extends BaseHandler<PurchaseFulfillmentItem, PurchaseFulfillmentItemDto> {

    public PurchaseFulfillmentItemsHandler(PurchaseFulfillmentItemsDbService db) {
        super(db);
    }
}
