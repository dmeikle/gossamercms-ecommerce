package com.gossamercms.products.adapters.inbound;



import com.gossamercms.mvc.controllers.BaseController;
import com.gossamercms.mvc.controllers.TypedBaseController;
import com.gossamercms.products.dtos.CategoryDto;
import com.gossamercms.products.dtos.requests.CategoryRequest;
import com.gossamercms.products.dtos.responses.AdminCategoryListResponse;
import com.gossamercms.products.dtos.responses.AdminCategoryResponse;
import com.gossamercms.products.dtos.responses.CategoryListResponse;
import com.gossamercms.products.dtos.responses.CategoryResponse;
import com.gossamercms.products.handlers.CategorysHandler;
import com.gossamercms.products.models.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-categories")
public class AdminCategorysController extends BaseController<Category, CategoryDto, CategoryRequest, AdminCategoryResponse, AdminCategoryListResponse> {

    public AdminCategorysController(CategorysHandler handler) {
        super(handler, AdminCategoryListResponse.class);
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
        protected AdminCategoryResponse toResponse(CategoryDto dto) {
            return AdminCategoryResponse.builder()
                           .name(dto.getName())
                           .slug(dto.getSlug())
                           .description(dto.getDescription())
                           .parentId(dto.getParentId())
                           .status(dto.getStatus())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
