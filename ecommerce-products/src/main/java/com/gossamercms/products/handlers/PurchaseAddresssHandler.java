package com.gossamercms.products.handlers;

import com.gossamercms.products.data.PurchaseAddressesDbService;
import com.gossamercms.products.dtos.PurchaseAddressDto;
import com.gossamercms.products.models.PurchaseAddress;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class PurchaseAddresssHandler extends BaseHandler<PurchaseAddress, PurchaseAddressDto> {

    public PurchaseAddresssHandler(PurchaseAddressesDbService db) {
        super(db);
    }
}
