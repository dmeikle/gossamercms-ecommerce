package com.gossamercms.products.handlers;

import com.gossamercms.mvc.data.ListResultset;
import com.gossamercms.mvc.data.QueryOptions;
import com.gossamercms.products.data.ProductsDbService;
import com.gossamercms.products.dtos.ProductDto;
import com.gossamercms.products.models.Product;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

import java.util.UUID;

@ModuleHandler
public class ProductsHandler extends BaseHandler<Product, ProductDto> {

    public ProductsHandler(ProductsDbService db) {
        super(db);
    }


}
