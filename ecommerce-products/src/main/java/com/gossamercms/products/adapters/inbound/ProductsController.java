package com.gossamercms.products.adapters.inbound;




import com.gossamercms.mvc.controllers.TypedBaseController;
import com.gossamercms.mvc.data.ListResultset;
import com.gossamercms.mvc.data.QueryFilter;
import com.gossamercms.mvc.data.QueryOptions;
import com.gossamercms.mvc.http.ApiResponse;
import com.gossamercms.products.dtos.CategoryDto;
import com.gossamercms.products.dtos.ProductDto;

import com.gossamercms.products.dtos.requests.ProductRequest;
import com.gossamercms.products.dtos.responses.ProductDetailResponse;
import com.gossamercms.products.dtos.responses.ProductListResponse;
import com.gossamercms.products.dtos.responses.ProductResponse;
import com.gossamercms.products.enums.ProductStatuses;
import com.gossamercms.products.handlers.ProductsHandler;
import com.gossamercms.products.models.Product;
import com.gossamercms.products.services.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/shop")
public class ProductsController extends TypedBaseController<Product, ProductDto, ProductRequest, ProductResponse, ProductListResponse> {

    private final ProductsService productsService;

    public ProductsController(ProductsHandler handler,
                              ProductsService productsService) {
        super(handler, ProductListResponse.class);
        this.productsService = productsService;
    }
    @Override
        protected ProductDto toDto(ProductRequest request) {
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
        protected ProductResponse toResponse(ProductDto dto) {
            return ProductResponse.builder()
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

        @GetMapping("/{category}")
        public ApiResponse<ListResultset<ProductResponse>> listProductsByCategory(
                @PathVariable("category") CategoryDto category,
                @RequestParam(name = "page",defaultValue = "1") int page,
                @RequestParam(name = "size",defaultValue = "50") int size,
                @RequestParam Map<String, Object> params) {
            params.remove("page");
            params.remove("size");
            //where ec.slug = 'electronics' and ep."status" = 'ACTIVE'
            QueryOptions options = QueryOptions.builder()
                    .size(size)
                    .page(page)
                    .queryFilters(List.of(
                            new QueryFilter("categoryId", QueryFilter.Operator.EQUALS, category.getId()),
                            new QueryFilter("ep.status", QueryFilter.Operator.EQUALS, ProductStatuses.ACTIVE.toString())
                    ))
                    .build();
            ListResultset<ProductResponse> productResponses = productsService.listProductsByCategory(options);

            return ApiResponse.ok(productResponses);
        }


    @GetMapping("/products/{product}")
    public ApiResponse<ProductDetailResponse> getProduct(
            @PathVariable("product") ProductDto product
    ) {
        return ApiResponse.ok(productsService.getProductDetails(product));
    }
}
