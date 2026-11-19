package com.gossamercms.products.adapters.inbound;


import com.gossamercms.products.dtos.PurchasePaymentDto;
import com.gossamercms.products.dtos.requests.PurchasePaymentRequest;
import com.gossamercms.products.dtos.responses.PurchasePaymentListResponse;
import com.gossamercms.products.dtos.responses.PurchasePaymentResponse;
import com.gossamercms.products.handlers.PurchasePaymentsHandler;
import com.gossamercms.products.models.PurchasePayment;
import com.gossamercms.mvc.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-purchase-payments")
public class PurchasePaymentsController extends BaseController<PurchasePayment, PurchasePaymentDto, PurchasePaymentRequest,  PurchasePaymentResponse, PurchasePaymentListResponse> {

    public PurchasePaymentsController(PurchasePaymentsHandler handler) {
        super(handler, PurchasePaymentListResponse.class);
    }
    @Override
        protected PurchasePaymentDto toDto(PurchasePaymentRequest request) {
            return PurchasePaymentDto.builder()
                        .purchaseId(request.getPurchaseId())
                        .provider(request.getProvider())
                        .paymentMethod(request.getPaymentMethod())
                        .providerPaymentId(request.getProviderPaymentId())
                        .providerCustomerId(request.getProviderCustomerId())
                        .amount(request.getAmount())
                        .currency(request.getCurrency())
                        .status(request.getStatus())
                        .failureCode(request.getFailureCode())
                        .failureMessage(request.getFailureMessage())
                        .paidAt(request.getPaidAt())
                        .idempotencyKey(request.getIdempotencyKey())
                        .metadata(request.getMetadata())
                        .build();

        }

        @Override
        protected PurchasePaymentResponse toResponse(PurchasePaymentDto dto) {
            return PurchasePaymentResponse.builder()
                           .purchaseId(dto.getPurchaseId())
                           .provider(dto.getProvider())
                           .paymentMethod(dto.getPaymentMethod())
                           .providerPaymentId(dto.getProviderPaymentId())
                           .providerCustomerId(dto.getProviderCustomerId())
                           .amount(dto.getAmount())
                           .currency(dto.getCurrency())
                           .status(dto.getStatus())
                           .failureCode(dto.getFailureCode())
                           .failureMessage(dto.getFailureMessage())
                           .paidAt(dto.getPaidAt())
                           .idempotencyKey(dto.getIdempotencyKey())
                           .metadata(dto.getMetadata())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
