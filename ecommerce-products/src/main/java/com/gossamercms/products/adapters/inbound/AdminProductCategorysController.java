package com.gossamercms.products.adapters.inbound;


import com.gossamercms.products.dtos.ProductCategoryDto;
import com.gossamercms.products.dtos.requests.ProductCategoryRequest;
import com.gossamercms.products.dtos.responses.ProductCategoryListResponse;
import com.gossamercms.products.dtos.responses.ProductCategoryResponse;
import com.gossamercms.products.handlers.ProductCategorysHandler;

import com.gossamercms.mvc.controllers.BaseController;
import com.gossamercms.products.models.ProductCategory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-product-categories")
public class AdminProductCategorysController extends BaseController<ProductCategory, ProductCategoryDto, ProductCategoryRequest, ProductCategoryResponse, ProductCategoryListResponse> {

    public AdminProductCategorysController(ProductCategorysHandler handler) {
        super(handler, ProductCategoryListResponse.class);
    }
    @Override
        protected ProductCategoryDto toDto(ProductCategoryRequest request) {
            return ProductCategoryDto.builder()
                        .productId(request.getProductId())
                        .categoryId(request.getCategoryId())
                        .build();

        }

        @Override
        protected ProductCategoryResponse toResponse(ProductCategoryDto dto) {
            return ProductCategoryResponse.builder()
                           .productId(dto.getProductId())
                           .categoryId(dto.getCategoryId())
                           .build();

        }
}
