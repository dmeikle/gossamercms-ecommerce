package com.gossamercms.products.adapters.inbound;

import com.gossamercms.products.dtos.PurchaseDto;

import com.gossamercms.mvc.controllers.BaseController;
import com.gossamercms.products.dtos.requests.PurchaseRequest;
import com.gossamercms.products.dtos.responses.PurchaseListResponse;
import com.gossamercms.products.dtos.responses.PurchaseResponse;
import com.gossamercms.products.handlers.PurchasesHandler;
import com.gossamercms.products.models.Purchase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-purchases")
public class PurchasesController extends BaseController<Purchase, PurchaseDto, PurchaseRequest, PurchaseResponse, PurchaseListResponse> {

    public PurchasesController(PurchasesHandler handler) {
        super(handler, PurchaseListResponse.class);
    }
    @Override
        protected PurchaseDto toDto(PurchaseRequest request) {
            return PurchaseDto.builder()
                        .purchaseNumber(request.getPurchaseNumber())
                        .customerContextId(request.getCustomerContextId())
                        .status(request.getStatus())
                        .totalAmount(request.getTotalAmount())
                        .currency(request.getCurrency())
                        .metadata(request.getMetadata())
                        .build();

        }

        @Override
        protected PurchaseResponse toResponse(PurchaseDto dto) {
            return PurchaseResponse.builder()
                           .purchaseNumber(dto.getPurchaseNumber())
                           .customerContextId(dto.getCustomerContextId())
                           .status(dto.getStatus())
                           .totalAmount(dto.getTotalAmount())
                           .currency(dto.getCurrency())
                           .metadata(dto.getMetadata())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
