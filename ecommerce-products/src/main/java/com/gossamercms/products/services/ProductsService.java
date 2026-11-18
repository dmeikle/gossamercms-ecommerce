package com.gossamercms.products.services;

import com.gossamercms.mvc.annotations.ModuleService;
import com.gossamercms.mvc.data.ListResultset;
import com.gossamercms.mvc.data.QueryFilter;
import com.gossamercms.mvc.data.QueryOptions;
import com.gossamercms.mvc.jwt.CurrentJwtUser;
import com.gossamercms.products.data.CategorysDbService;
import com.gossamercms.products.data.ProductCategorysDbService;
import com.gossamercms.products.data.ProductsDbService;
import com.gossamercms.products.dtos.CategoryDto;
import com.gossamercms.products.dtos.ProductCategoryDto;
import com.gossamercms.products.dtos.ProductDto;
import com.gossamercms.products.dtos.requests.AdminProductRequest;
import com.gossamercms.products.dtos.responses.AdminCategoryResponse;
import com.gossamercms.products.dtos.responses.AdminProductResponse;
import com.gossamercms.products.dtos.responses.ProductDetailResponse;
import com.gossamercms.products.dtos.responses.ProductResponse;
import com.gossamercms.products.enums.ProductStatuses;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@ModuleService
@RequiredArgsConstructor
public class ProductsService {
    private final ProductCategorysDbService productCategorysDbService;
    private final CategorysDbService categorysDbService;
    private final ProductsDbService productsDbService;

    public AdminProductResponse create(CurrentJwtUser jwtUser, AdminProductRequest request) {
        ListResultset<CategoryDto> categories = categorysDbService.getAll(
                QueryOptions.builder().queryFilters(
                        List.of(new QueryFilter("id", QueryFilter.Operator.IN, request.getCategories()))
                ).build());
        if(categories.totalCount() != request.getCategories().size()) {
            throw new IllegalArgumentException("One or more categories do not exist");
        }

        var product = productsDbService.create(jwtUser.getUserContextId(),
                ProductDto.builder()
                    .name(request.getName())
                    .slug(request.getSlug())
                    .description(request.getDescription())
                    .price(request.getPrice())
                    .metadata(request.getMetadata())
                    .currency(request.getCurrency())
                    .sku(request.getSku())
                    .status(ProductStatuses.ACTIVE.toString())
                    .thumbnailUrl(request.getThumbnailUrl())
                    .build());

        var productCategories = productCategorysDbService.createOrReplaceBulk(
            jwtUser.getUserContextId(),
            request.getCategories().stream().map(categoryId -> ProductCategoryDto.builder()
                .categoryId(categoryId)
                .productId(product.getId())
            .build()).toList(),
            Map.of());

        return AdminProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .description(product.getDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .sku(product.getSku())
                .status(product.getStatus())
                .metadata(product.getMetadata())
                .thumbnailUrl(product.getThumbnailUrl())
                .createdAt(product.getCreatedAt())
                .categories(categories.list().stream().map(category -> AdminCategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .build()).toList())
            .build();
    }

    public ListResultset<ProductResponse> listProductsByCategory(QueryOptions options) {
        ListResultset<ProductDto> productDtos = productsDbService.listProductsByCategory(
                options
        );
        return new ListResultset<>(productDtos.list().stream().map(product -> ProductResponse.builder()
                .name(product.getName())
                .slug(product.getSlug())
                .description(product.getDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .sku(product.getSku())
                .status(product.getStatus())
                .metadata(product.getMetadata())
                .thumbnailUrl(product.getThumbnailUrl())
                .createdAt(product.getCreatedAt())
                .build()).toList(),
                productDtos.page(),
                productDtos.pageSize(),
                productDtos.totalCount(),
                productDtos.totalPages()
                );

    }

    public ProductDetailResponse getProductDetails(ProductDto product) {
        return ProductDetailResponse.builder()
                .product(ProductResponse.builder()
                        .name(product.getName())
                        .slug(product.getSlug())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .currency(product.getCurrency())
                        .sku(product.getSku())
                        .status(product.getStatus())
                        .metadata(product.getMetadata())
                        .thumbnailUrl(product.getThumbnailUrl())
                        .createdAt(product.getCreatedAt())
                        .build())
                .build();
    }


}
