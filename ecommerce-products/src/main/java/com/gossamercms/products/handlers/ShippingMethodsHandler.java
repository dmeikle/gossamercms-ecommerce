package com.gossamercms.products.handlers;

import com.gossamercms.products.data.ShippingMethodsDbService;
import com.gossamercms.products.dtos.ShippingMethodDto;
import com.gossamercms.products.models.ShippingMethod;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class ShippingMethodsHandler extends BaseHandler<ShippingMethod, ShippingMethodDto> {

    public ShippingMethodsHandler(ShippingMethodsDbService db) {
        super(db);
    }
}
