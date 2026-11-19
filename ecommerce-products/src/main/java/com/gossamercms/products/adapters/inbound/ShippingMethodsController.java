package com.gossamercms.products.adapters.inbound;


import com.gossamercms.products.dtos.ShippingMethodDto;
import com.gossamercms.products.dtos.requests.ShippingMethodRequest;
import com.gossamercms.products.dtos.responses.ShippingMethodListResponse;
import com.gossamercms.products.dtos.responses.ShippingMethodResponse;
import com.gossamercms.products.handlers.ShippingMethodsHandler;
import com.gossamercms.products.models.ShippingMethod;
import com.gossamercms.mvc.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-shipping-methods")
public class ShippingMethodsController extends BaseController<ShippingMethod, ShippingMethodDto, ShippingMethodRequest,  ShippingMethodResponse, ShippingMethodListResponse> {

    public ShippingMethodsController(ShippingMethodsHandler handler) {
        super(handler, ShippingMethodListResponse.class);
    }
    @Override
        protected ShippingMethodDto toDto(ShippingMethodRequest request) {
            return ShippingMethodDto.builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .methodType(request.getMethodType())
                        .price(request.getPrice())
                        .currency(request.getCurrency())
                        .isActive(request.getIsActive())
                        .metadata(request.getMetadata())
                        .build();

        }

        @Override
        protected ShippingMethodResponse toResponse(ShippingMethodDto dto) {
            return ShippingMethodResponse.builder()
                           .name(dto.getName())
                           .description(dto.getDescription())
                           .methodType(dto.getMethodType())
                           .price(dto.getPrice())
                           .currency(dto.getCurrency())
                           .isActive(dto.getIsActive())
                           .metadata(dto.getMetadata())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
