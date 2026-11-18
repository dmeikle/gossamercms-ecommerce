package com.gossamercms.products.services;

import com.gossamercms.mvc.annotations.ModuleService;
import com.gossamercms.mvc.data.ListResultset;
import com.gossamercms.mvc.jwt.CurrentJwtUser;
import com.gossamercms.products.data.CartItemsDbService;
import com.gossamercms.products.data.CartsDbService;
import com.gossamercms.products.dtos.CartDto;
import com.gossamercms.products.dtos.CartItemDto;
import com.gossamercms.products.dtos.ProductDto;
import com.gossamercms.products.dtos.requests.CartItemRequest;
import com.gossamercms.products.dtos.responses.CartItemResponse;
import com.gossamercms.products.dtos.responses.CartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ModuleService
@RequiredArgsConstructor
public class CartsService {
    private static final int CART_ITEMS_PAGE_SIZE = 100;

    private final CartsDbService cartsDbService;
    private final CartItemsDbService cartItemsDbService;

    public CartResponse getCartByUserContextId(UUID userContextId) {
        CartDto cart = cartsDbService.get(Map.of("userContextId", userContextId));
        if (cart == null || cart.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found");
        }

        return getCartItems(cart);
    }

    public CartResponse getCartBySessionId(String sessionId) {
        CartDto cart = cartsDbService.findGuestCart(requireSessionId(sessionId));
        if (cart == null || cart.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found");
        }

        return getCartItems(cart);
    }

    private CartResponse getCartItems(CartDto cart) {
        List<CartItemResponse> items = new ArrayList<>();
        ListResultset<CartItemResponse> result;
        int page = 1;
        do {
            result = cartItemsDbService.getCartItemsByCartId(cart.getId(), page, CART_ITEMS_PAGE_SIZE);
            items.addAll(result.list());
        } while (page++ < result.totalPages());

        return CartResponse.builder()
                .id(cart.getId())
                .items(items)
                .build();
    }

    public CartResponse addToCart(CurrentJwtUser jwtUser, ProductDto product, CartItemRequest request) {
        //first check if the user has a cart, if not create one
        CartDto cart = getOrCreateCart(jwtUser);

        //then add the item to the cart
        cartItemsDbService.create(jwtUser.getUserContextId(), CartItemDto.builder()
                        .cartId(cart.getId())
                        .productId(product.getId())
                        .quantity(request.getQuantity())
                        .build());
        return getCartItems(cart);
    }

    private CartDto getOrCreateCart(CurrentJwtUser jwtUser) {
        UUID userContextId = jwtUser.getUserContextId();
        String sessionId = jwtUser.getSessionId();
        CartDto cart = userContextId == null
                ? cartsDbService.findGuestCart(requireSessionId(sessionId))
                : cartsDbService.get(Map.of("userContextId", userContextId));
        if (cart == null || cart.getId() == null) {
            cart = cartsDbService.create(userContextId,
                    CartDto.builder()
                            .userContextId(userContextId)
                            .sessionId(sessionId)
                            .build());
        }
        return cart;
    }

    private static String requireSessionId(String sessionId) {
        if (sessionId == null || sessionId.isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return sessionId;
    }
}
