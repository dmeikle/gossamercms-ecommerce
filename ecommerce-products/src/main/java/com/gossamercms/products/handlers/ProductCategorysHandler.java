package com.gossamercms.products.handlers;

import com.gossamercms.products.data.ProductCategorysDbService;
import com.gossamercms.products.dtos.ProductCategoryDto;
import com.gossamercms.products.models.ProductCategory;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class ProductCategorysHandler extends BaseHandler<ProductCategory, ProductCategoryDto> {

    public ProductCategorysHandler(ProductCategorysDbService db) {
        super(db);
    }
}
