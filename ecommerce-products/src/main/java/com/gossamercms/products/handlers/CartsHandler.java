package com.gossamercms.products.handlers;

import com.gossamercms.products.data.CartsDbService;
import com.gossamercms.products.dtos.CartDto;
import com.gossamercms.products.models.Cart;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class CartsHandler extends BaseHandler<Cart, CartDto> {

    public CartsHandler(CartsDbService db) {
        super(db);
    }
}
