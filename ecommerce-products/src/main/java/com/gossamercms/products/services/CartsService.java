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
import com.gossamercms.products.dtos.responses.CartSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
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

        BigDecimal subtotal = items.stream()
                .map(CartItemResponse::getSubtotal)
                .filter(java.util.Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discountTotal = BigDecimal.ZERO;
        BigDecimal shippingTotal = BigDecimal.ZERO;
        BigDecimal taxTotal = BigDecimal.ZERO;
        BigDecimal grandTotal = subtotal.subtract(discountTotal)
                .add(shippingTotal)
                .add(taxTotal);

        return CartResponse.builder()
                .id(cart.getId())
                .summary(CartSummaryResponse.builder()
                        .currency("CAD")
                        .discountTotal(discountTotal)
                        .grandTotal(grandTotal)
                        .itemCount(items.size())
                        .shippingTotal(shippingTotal)
                        .subtotal(subtotal)
                        .taxTotal(taxTotal)
                        .build())
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
        CartDto cart = null;
        try {
            if (userContextId == null) {
                cart = cartsDbService.findGuestCart(requireSessionId(sessionId));
            } else {
                cart = cartsDbService.get(Map.of("userContextId", userContextId));
            }
        } catch (Exception e) {
            //log the exception
        }
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
