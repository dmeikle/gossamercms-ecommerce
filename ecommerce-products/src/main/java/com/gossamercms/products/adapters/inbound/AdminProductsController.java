package com.gossamercms.products.adapters.inbound;


import com.gossamercms.mvc.annotations.CurrentUser;
import com.gossamercms.mvc.controllers.BaseController;
import com.gossamercms.mvc.data.ListResultset;
import com.gossamercms.mvc.exceptions.UnauthorizedStatusException;
import com.gossamercms.mvc.http.ApiResponse;
import com.gossamercms.mvc.jwt.CurrentJwtUser;
import com.gossamercms.products.dtos.ProductCategoryDto;
import com.gossamercms.products.dtos.ProductDto;
import com.gossamercms.products.dtos.requests.AdminProductRequest;
import com.gossamercms.products.dtos.responses.AdminProductListResponse;
import com.gossamercms.products.dtos.responses.AdminProductResponse;
import com.gossamercms.products.handlers.ProductCategorysHandler;
import com.gossamercms.products.handlers.ProductsHandler;
import com.gossamercms.products.models.Product;
import com.gossamercms.products.services.ProductsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/admin/ecommerce-products")
public class AdminProductsController extends BaseController<Product, ProductDto, AdminProductRequest, AdminProductResponse, AdminProductListResponse> {

    private final ProductsService productsService;

    public AdminProductsController(ProductsHandler handler,
                                   ProductsService productsService) {
        super(handler, AdminProductListResponse.class);
        this.productsService = productsService;
    }
    @Override
        protected ProductDto toDto(AdminProductRequest request) {
            return ProductDto.builder()
                        .sku(request.getSku())
                        .name(request.getName())
                        .description(request.getDescription())
                        .price(request.getPrice())
                        .currency(request.getCurrency())
                        .status(request.getStatus())
                        .metadata(request.getMetadata())
                        .thumbnailUrl(request.getThumbnailUrl())
                        .build();

        }

        @Override
        protected AdminProductResponse toResponse(ProductDto dto) {
            return AdminProductResponse.builder()
                           .sku(dto.getSku())
                           .name(dto.getName())
                           .description(dto.getDescription())
                           .price(dto.getPrice())
                           .currency(dto.getCurrency())
                           .status(dto.getStatus())
                           .metadata(dto.getMetadata())
                           .thumbnailUrl(dto.getThumbnailUrl())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }

    @PostMapping
    public ApiResponse<AdminProductResponse> create(@CurrentUser CurrentJwtUser jwtUser, @RequestBody AdminProductRequest request) {
        if (!jwtUser.isAuthenticated()) {
            throw new UnauthorizedStatusException();
        } else {
            return ApiResponse.ok(productsService.create(jwtUser, request));
        }
    }
}
