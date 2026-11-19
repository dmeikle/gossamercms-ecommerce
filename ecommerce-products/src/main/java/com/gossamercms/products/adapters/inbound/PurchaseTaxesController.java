package com.gossamercms.products.adapters.inbound;


import com.gossamercms.products.dtos.PurchaseTaxDto;
import com.gossamercms.products.dtos.requests.PurchaseTaxRequest;
import com.gossamercms.products.dtos.responses.PurchaseTaxListResponse;
import com.gossamercms.products.dtos.responses.PurchaseTaxResponse;
import com.gossamercms.products.handlers.PurchaseTaxsHandler;
import com.gossamercms.products.models.PurchaseTax;
import com.gossamercms.mvc.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-purchase-taxes")
public class PurchaseTaxesController extends BaseController<PurchaseTax, PurchaseTaxDto, PurchaseTaxRequest,  PurchaseTaxResponse, PurchaseTaxListResponse> {

    public PurchaseTaxesController(PurchaseTaxsHandler handler) {
        super(handler, PurchaseTaxListResponse.class);
    }
    @Override
        protected PurchaseTaxDto toDto(PurchaseTaxRequest request) {
            return PurchaseTaxDto.builder()
                        .purchaseId(request.getPurchaseId())
                        .purchaseItemId(request.getPurchaseItemId())
                        .taxName(request.getTaxName())
                        .taxType(request.getTaxType())
                        .jurisdiction(request.getJurisdiction())
                        .jurisdictionCode(request.getJurisdictionCode())
                        .taxRate(request.getTaxRate())
                        .taxableAmount(request.getTaxableAmount())
                        .taxAmount(request.getTaxAmount())
                        .provider(request.getProvider())
                        .providerReference(request.getProviderReference())
                        .build();

        }

        @Override
        protected PurchaseTaxResponse toResponse(PurchaseTaxDto dto) {
            return PurchaseTaxResponse.builder()
                           .purchaseId(dto.getPurchaseId())
                           .purchaseItemId(dto.getPurchaseItemId())
                           .taxName(dto.getTaxName())
                           .taxType(dto.getTaxType())
                           .jurisdiction(dto.getJurisdiction())
                           .jurisdictionCode(dto.getJurisdictionCode())
                           .taxRate(dto.getTaxRate())
                           .taxableAmount(dto.getTaxableAmount())
                           .taxAmount(dto.getTaxAmount())
                           .provider(dto.getProvider())
                           .providerReference(dto.getProviderReference())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
