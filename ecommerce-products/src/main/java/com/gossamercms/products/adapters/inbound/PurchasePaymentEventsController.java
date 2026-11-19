package com.gossamercms.products.adapters.inbound;


import com.gossamercms.products.dtos.PurchasePaymentEventDto;
import com.gossamercms.products.dtos.requests.PurchasePaymentEventRequest;
import com.gossamercms.products.dtos.responses.PurchasePaymentEventListResponse;
import com.gossamercms.products.dtos.responses.PurchasePaymentEventResponse;
import com.gossamercms.products.handlers.PurchasePaymentEventsHandler;
import com.gossamercms.products.models.PurchasePaymentEvent;
import com.gossamercms.mvc.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-purchase-payment-events")
public class PurchasePaymentEventsController extends BaseController<PurchasePaymentEvent, PurchasePaymentEventDto, PurchasePaymentEventRequest,  PurchasePaymentEventResponse, PurchasePaymentEventListResponse> {

    public PurchasePaymentEventsController(PurchasePaymentEventsHandler handler) {
        super(handler, PurchasePaymentEventListResponse.class);
    }
    @Override
        protected PurchasePaymentEventDto toDto(PurchasePaymentEventRequest request) {
            return PurchasePaymentEventDto.builder()
                        .paymentId(request.getPaymentId())
                        .provider(request.getProvider())
                        .providerEventId(request.getProviderEventId())
                        .eventType(request.getEventType())
                        .processingStatus(request.getProcessingStatus())
                        .payload(request.getPayload())
                        .processedAt(request.getProcessedAt())
                        .errorMessage(request.getErrorMessage())
                        .build();

        }

        @Override
        protected PurchasePaymentEventResponse toResponse(PurchasePaymentEventDto dto) {
            return PurchasePaymentEventResponse.builder()
                           .paymentId(dto.getPaymentId())
                           .provider(dto.getProvider())
                           .providerEventId(dto.getProviderEventId())
                           .eventType(dto.getEventType())
                           .processingStatus(dto.getProcessingStatus())
                           .payload(dto.getPayload())
                           .processedAt(dto.getProcessedAt())
                           .errorMessage(dto.getErrorMessage())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
