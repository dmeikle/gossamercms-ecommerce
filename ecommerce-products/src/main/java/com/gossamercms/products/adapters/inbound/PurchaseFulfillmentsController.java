package com.gossamercms.products.adapters.inbound;


import com.gossamercms.products.dtos.PurchaseFulfillmentDto;
import com.gossamercms.products.dtos.requests.PurchaseFulfillmentRequest;
import com.gossamercms.products.dtos.responses.PurchaseFulfillmentListResponse;
import com.gossamercms.products.dtos.responses.PurchaseFulfillmentResponse;
import com.gossamercms.products.handlers.PurchaseFulfillmentsHandler;
import com.gossamercms.products.models.PurchaseFulfillment;
import com.gossamercms.mvc.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-purchase-fulfillments")
public class PurchaseFulfillmentsController extends BaseController<PurchaseFulfillment, PurchaseFulfillmentDto, PurchaseFulfillmentRequest,  PurchaseFulfillmentResponse, PurchaseFulfillmentListResponse> {

    public PurchaseFulfillmentsController(PurchaseFulfillmentsHandler handler) {
        super(handler, PurchaseFulfillmentListResponse.class);
    }
    @Override
        protected PurchaseFulfillmentDto toDto(PurchaseFulfillmentRequest request) {
            return PurchaseFulfillmentDto.builder()
                        .purchaseId(request.getPurchaseId())
                        .shippingMethodId(request.getShippingMethodId())
                        .fulfillmentType(request.getFulfillmentType())
                        .status(request.getStatus())
                        .trackingNumber(request.getTrackingNumber())
                        .trackingUrl(request.getTrackingUrl())
                        .carrier(request.getCarrier())
                        .pickupReference(request.getPickupReference())
                        .shippedAt(request.getShippedAt())
                        .deliveredAt(request.getDeliveredAt())
                        .collectedAt(request.getCollectedAt())
                        .completedAt(request.getCompletedAt())
                        .metadata(request.getMetadata())
                        .build();

        }

        @Override
        protected PurchaseFulfillmentResponse toResponse(PurchaseFulfillmentDto dto) {
            return PurchaseFulfillmentResponse.builder()
                           .purchaseId(dto.getPurchaseId())
                           .shippingMethodId(dto.getShippingMethodId())
                           .fulfillmentType(dto.getFulfillmentType())
                           .status(dto.getStatus())
                           .trackingNumber(dto.getTrackingNumber())
                           .trackingUrl(dto.getTrackingUrl())
                           .carrier(dto.getCarrier())
                           .pickupReference(dto.getPickupReference())
                           .shippedAt(dto.getShippedAt())
                           .deliveredAt(dto.getDeliveredAt())
                           .collectedAt(dto.getCollectedAt())
                           .completedAt(dto.getCompletedAt())
                           .metadata(dto.getMetadata())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
