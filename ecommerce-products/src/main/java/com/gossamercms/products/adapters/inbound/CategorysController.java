package com.gossamercms.products.adapters.inbound;



import com.gossamercms.mvc.controllers.TypedBaseController;
import com.gossamercms.products.dtos.CategoryDto;
import com.gossamercms.products.dtos.requests.CategoryRequest;
import com.gossamercms.products.dtos.responses.CategoryListResponse;
import com.gossamercms.products.dtos.responses.CategoryResponse;
import com.gossamercms.products.handlers.CategorysHandler;
import com.gossamercms.products.models.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ecommerce-categories")
public class CategorysController extends TypedBaseController<Category, CategoryDto, CategoryRequest, CategoryResponse, CategoryListResponse> {

    public CategorysController(CategorysHandler handler) {
        super(handler, CategoryListResponse.class);
    }
    @Override
        protected CategoryDto toDto(CategoryRequest request) {
            return CategoryDto.builder()
                        .name(request.getName())
                        .slug(request.getSlug())
                        .description(request.getDescription())
                        .parentId(request.getParentId())
                        .status(request.getStatus())
                        .build();

        }

        @Override
        protected CategoryResponse toResponse(CategoryDto dto) {
            return CategoryResponse.builder()
                           .name(dto.getName())
                           .slug(dto.getSlug())
                           .description(dto.getDescription())
                           .parentId(dto.getParentId())
                           .status(dto.getStatus())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
