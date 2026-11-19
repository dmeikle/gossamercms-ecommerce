package com.gossamercms.products.handlers;

import com.gossamercms.products.data.PurchaseTaxsDbService;
import com.gossamercms.products.dtos.PurchaseTaxDto;
import com.gossamercms.products.models.PurchaseTax;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class PurchaseTaxsHandler extends BaseHandler<PurchaseTax, PurchaseTaxDto> {

    public PurchaseTaxsHandler(PurchaseTaxsDbService db) {
        super(db);
    }
}
