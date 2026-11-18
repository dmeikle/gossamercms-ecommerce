package com.gossamercms.products.adapters.inbound;


import com.gossamercms.products.dtos.PurchaseItemDto;
import com.gossamercms.products.dtos.requests.PurchaseItemRequest;
import com.gossamercms.products.dtos.responses.PurchaseItemListResponse;
import com.gossamercms.products.dtos.responses.PurchaseItemResponse;
import com.gossamercms.products.handlers.PurchaseItemsHandler;

import com.gossamercms.mvc.controllers.BaseController;
import com.gossamercms.products.models.PurchaseItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-purchase-items")
public class PurchaseItemsController extends BaseController<PurchaseItem, PurchaseItemDto, PurchaseItemRequest, PurchaseItemResponse, PurchaseItemListResponse> {

    public PurchaseItemsController(PurchaseItemsHandler handler) {
        super(handler, PurchaseItemListResponse.class);
    }
    @Override
        protected PurchaseItemDto toDto(PurchaseItemRequest request) {
            return PurchaseItemDto.builder()
                        .purchaseId(request.getPurchaseId())
                        .productId(request.getProductId())
                        .quantity(request.getQuantity())
                        .unitPrice(request.getUnitPrice())
                        .totalPrice(request.getTotalPrice())
                        .currency(request.getCurrency())
                        .metadata(request.getMetadata())
                        .build();

        }

        @Override
        protected PurchaseItemResponse toResponse(PurchaseItemDto dto) {
            return PurchaseItemResponse.builder()
                           .purchaseId(dto.getPurchaseId())
                           .productId(dto.getProductId())
                           .quantity(dto.getQuantity())
                           .unitPrice(dto.getUnitPrice())
                           .totalPrice(dto.getTotalPrice())
                           .currency(dto.getCurrency())
                           .metadata(dto.getMetadata())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
