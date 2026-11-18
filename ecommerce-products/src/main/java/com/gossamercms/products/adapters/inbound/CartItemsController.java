package com.gossamercms.products.adapters.inbound;

import com.gossamercms.mvc.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gossamercms.products.models.CartItem;
import com.gossamercms.products.dtos.requests.CartItemRequest;
import com.gossamercms.products.dtos.responses.CartItemResponse;
import com.gossamercms.products.dtos.responses.CartItemListResponse;
import com.gossamercms.products.dtos.CartItemDto;
import com.gossamercms.products.handlers.CartItemsHandler;

@RestController
@RequestMapping("/admin/ecommerce-cart-items")
public class CartItemsController extends BaseController<CartItem, CartItemDto, CartItemRequest,  CartItemResponse, CartItemListResponse> {

    public CartItemsController(CartItemsHandler handler) {
        super(handler, CartItemListResponse.class);
    }
    @Override
        protected CartItemDto toDto(CartItemRequest request) {
            return CartItemDto.builder()
                        .quantity(request.getQuantity())
                        .build();

        }

        @Override
        protected CartItemResponse toResponse(CartItemDto dto) {
            return CartItemResponse.builder()
                           .id(dto.getId())
                           .cartId(dto.getCartId())
                           .productId(dto.getProductId())
                           .quantity(dto.getQuantity())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
