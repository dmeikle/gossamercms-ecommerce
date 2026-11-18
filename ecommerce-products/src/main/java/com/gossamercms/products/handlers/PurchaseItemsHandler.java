package com.gossamercms.products.handlers;

import com.gossamercms.products.data.PurchaseItemsDbService;
import com.gossamercms.products.dtos.PurchaseItemDto;
import com.gossamercms.products.models.PurchaseItem;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class PurchaseItemsHandler extends BaseHandler<PurchaseItem, PurchaseItemDto> {

    public PurchaseItemsHandler(PurchaseItemsDbService db) {
        super(db);
    }
}
