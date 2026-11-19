package com.gossamercms.products.adapters.inbound;


import com.gossamercms.products.dtos.PurchaseRefundDto;
import com.gossamercms.products.dtos.requests.PurchaseRefundRequest;
import com.gossamercms.products.dtos.responses.PurchaseRefundListResponse;
import com.gossamercms.products.dtos.responses.PurchaseRefundResponse;
import com.gossamercms.products.handlers.PurchaseRefundsHandler;
import com.gossamercms.products.models.PurchaseRefund;
import com.gossamercms.mvc.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-purchase-refunds")
public class PurchaseRefundsController extends BaseController<PurchaseRefund, PurchaseRefundDto, PurchaseRefundRequest,  PurchaseRefundResponse, PurchaseRefundListResponse> {

    public PurchaseRefundsController(PurchaseRefundsHandler handler) {
        super(handler, PurchaseRefundListResponse.class);
    }
    @Override
        protected PurchaseRefundDto toDto(PurchaseRefundRequest request) {
            return PurchaseRefundDto.builder()
                        .purchaseId(request.getPurchaseId())
                        .paymentId(request.getPaymentId())
                        .providerRefundId(request.getProviderRefundId())
                        .amount(request.getAmount())
                        .currency(request.getCurrency())
                        .status(request.getStatus())
                        .reason(request.getReason())
                        .description(request.getDescription())
                        .provider(request.getProvider())
                        .failureCode(request.getFailureCode())
                        .failureMessage(request.getFailureMessage())
                        .refundedAt(request.getRefundedAt())
                        .requestedBy(request.getRequestedBy())
                        .metadata(request.getMetadata())
                        .build();

        }

        @Override
        protected PurchaseRefundResponse toResponse(PurchaseRefundDto dto) {
            return PurchaseRefundResponse.builder()
                           .purchaseId(dto.getPurchaseId())
                           .paymentId(dto.getPaymentId())
                           .providerRefundId(dto.getProviderRefundId())
                           .amount(dto.getAmount())
                           .currency(dto.getCurrency())
                           .status(dto.getStatus())
                           .reason(dto.getReason())
                           .description(dto.getDescription())
                           .provider(dto.getProvider())
                           .failureCode(dto.getFailureCode())
                           .failureMessage(dto.getFailureMessage())
                           .refundedAt(dto.getRefundedAt())
                           .requestedBy(dto.getRequestedBy())
                           .metadata(dto.getMetadata())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
