package com.gossamercms.products.adapters.inbound;


import com.gossamercms.products.dtos.PurchaseFulfillmentItemDto;
import com.gossamercms.products.dtos.requests.PurchaseFulfillmentItemRequest;
import com.gossamercms.products.dtos.responses.PurchaseFulfillmentItemListResponse;
import com.gossamercms.products.dtos.responses.PurchaseFulfillmentItemResponse;
import com.gossamercms.products.handlers.PurchaseFulfillmentItemsHandler;
import com.gossamercms.products.models.PurchaseFulfillmentItem;
import com.gossamercms.mvc.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-purchase-fulfillment-items")
public class PurchaseFulfillmentItemsController extends BaseController<PurchaseFulfillmentItem, PurchaseFulfillmentItemDto, PurchaseFulfillmentItemRequest,  PurchaseFulfillmentItemResponse, PurchaseFulfillmentItemListResponse> {

    public PurchaseFulfillmentItemsController(PurchaseFulfillmentItemsHandler handler) {
        super(handler, PurchaseFulfillmentItemListResponse.class);
    }
    @Override
        protected PurchaseFulfillmentItemDto toDto(PurchaseFulfillmentItemRequest request) {
            return PurchaseFulfillmentItemDto.builder()
                        .fulfillmentId(request.getFulfillmentId())
                        .purchaseItemId(request.getPurchaseItemId())
                        .quantity(request.getQuantity())
                        .build();

        }

        @Override
        protected PurchaseFulfillmentItemResponse toResponse(PurchaseFulfillmentItemDto dto) {
            return PurchaseFulfillmentItemResponse.builder()
                           .fulfillmentId(dto.getFulfillmentId())
                           .purchaseItemId(dto.getPurchaseItemId())
                           .quantity(dto.getQuantity())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
