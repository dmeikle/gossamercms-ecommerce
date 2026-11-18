package com.gossamercms.products.handlers;

import com.gossamercms.products.data.CartItemsDbService;
import com.gossamercms.products.dtos.CartItemDto;
import com.gossamercms.products.models.CartItem;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class CartItemsHandler extends BaseHandler<CartItem, CartItemDto> {

    public CartItemsHandler(CartItemsDbService db) {
        super(db);
    }
}
