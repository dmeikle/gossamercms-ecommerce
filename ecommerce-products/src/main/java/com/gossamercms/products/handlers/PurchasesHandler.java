package com.gossamercms.products.handlers;

import com.gossamercms.products.data.PurchasesDbService;
import com.gossamercms.products.dtos.PurchaseDto;
import com.gossamercms.products.models.Purchase;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class PurchasesHandler extends BaseHandler<Purchase, PurchaseDto> {

    public PurchasesHandler(PurchasesDbService db) {
        super(db);
    }
}
