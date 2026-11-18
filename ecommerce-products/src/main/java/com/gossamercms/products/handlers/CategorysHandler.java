package com.gossamercms.products.handlers;

import com.gossamercms.products.data.CategorysDbService;
import com.gossamercms.products.dtos.CategoryDto;
import com.gossamercms.products.models.Category;
import com.gossamercms.mvc.annotations.ModuleHandler;
import com.gossamercms.mvc.handlers.BaseHandler;

@ModuleHandler
public class CategorysHandler extends BaseHandler<Category, CategoryDto> {

    public CategorysHandler(CategorysDbService db) {
        super(db);
    }
}
