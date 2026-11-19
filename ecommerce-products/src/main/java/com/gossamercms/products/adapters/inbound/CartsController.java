package com.gossamercms.products.adapters.inbound;


import com.gossamercms.mvc.annotations.CurrentUser;
import com.gossamercms.mvc.controllers.TypedBaseController;
import com.gossamercms.mvc.http.ApiResponse;
import com.gossamercms.mvc.jwt.CurrentJwtUser;
import com.gossamercms.products.dtos.CartDto;
import com.gossamercms.products.dtos.ProductDto;
import com.gossamercms.products.dtos.requests.CartItemRequest;
import com.gossamercms.products.dtos.requests.CartRequest;
import com.gossamercms.products.dtos.requests.CheckoutRequest;
import com.gossamercms.products.dtos.responses.CartResponse;
import com.gossamercms.products.dtos.responses.CartListResponse;
import com.gossamercms.products.dtos.responses.CheckoutResponse;
import com.gossamercms.products.handlers.CartsHandler;
import com.gossamercms.products.models.Cart;
import com.gossamercms.products.services.CartsService;
import com.gossamercms.products.services.CheckoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/cart")
public class CartsController extends TypedBaseController<Cart, CartDto, CartRequest,  CartResponse, CartListResponse> {
    private static final String GUEST_CART_SESSION_ID = CartsController.class.getName() + ".guestCartSessionId";

    private final CartsService cartsService;
    private final CheckoutService checkoutService;

    public CartsController(CartsHandler handler, CartsService cartsService, CheckoutService checkoutService) {
        super(handler, CartListResponse.class);
        this.cartsService = cartsService;
        this.checkoutService = checkoutService;
    }

    @Override
    protected CartDto toDto(CartRequest request) {
        return CartDto.builder()
                .userContextId(request.getUserContextId())
                .sessionId(request.getSessionId())
                .status(request.getStatus())
                .expiresAt(request.getExpiresAt())
                .build();
    }

    @Override
    protected CartResponse toResponse(CartDto dto) {
        return CartResponse.builder()
                .id(dto.getId())
                .items(List.of())
                .build();
    }

    @GetMapping("/view")
    public ApiResponse<CartResponse> viewCart(@CurrentUser CurrentJwtUser jwtUser,
                                             HttpServletRequest servletRequest) {
        CurrentJwtUser cartUser = jwtUser == null ? guestCartUser(servletRequest, false) : jwtUser;
        if (cartUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        CartResponse cartResponse = cartUser.getUserContextId() != null ?
                cartsService.getCartByUserContextId(cartUser.getUserContextId()) :
                cartsService.getCartBySessionId(cartUser.getSessionId());

        return ApiResponse.ok(cartResponse);
    }

    @PostMapping("/add/{product}")
    public CartResponse addToCart(@CurrentUser CurrentJwtUser jwtUser,
                                  @PathVariable("product") ProductDto product,
                                  @RequestBody CartItemRequest request,
                                  HttpServletRequest servletRequest) {
        CurrentJwtUser cartUser = jwtUser == null ? guestCartUser(servletRequest, true) : jwtUser;
        return cartsService.addToCart(cartUser, product, request);
    }

    private static GuestCartUser guestCartUser(HttpServletRequest request, boolean create) {
        HttpSession session = request.getSession(create);
        if (session == null) {
            return null;
        }
        String sessionId = (String) session.getAttribute(GUEST_CART_SESSION_ID);
        if (sessionId == null && create) {
            sessionId = UUID.randomUUID().toString();
            session.setAttribute(GUEST_CART_SESSION_ID, sessionId);
        }
        return sessionId == null ? null : new GuestCartUser(sessionId);
    }

    @PostMapping("/checkout")
    public ApiResponse<CheckoutResponse> checkout(@CurrentUser CurrentJwtUser jwtUser,
                                                  @RequestBody CheckoutRequest request,
                                                  HttpServletRequest servletRequest) {

        return ApiResponse.ok(checkoutService.checkout(jwtUser, request));
    }

    private record GuestCartUser(String sessionId) implements CurrentJwtUser {
        @Override public String getSessionId() { return sessionId; }
        @Override public UUID getUserId() { return null; }
        @Override public UUID getUserContextId() { return null; }
        @Override public String getIdentifier() { return null; }
        @Override public String[] getRoles() { return new String[0]; }
        @Override public String[] getPermissions() { return new String[0]; }
    }
}
