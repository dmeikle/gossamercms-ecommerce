package com.gossamercms.products.adapters.inbound;


import com.gossamercms.products.dtos.PurchaseAddressDto;
import com.gossamercms.products.dtos.requests.PurchaseAddressRequest;
import com.gossamercms.products.dtos.responses.PurchaseAddressListResponse;
import com.gossamercms.products.dtos.responses.PurchaseAddressResponse;
import com.gossamercms.products.handlers.PurchaseAddresssHandler;
import com.gossamercms.products.models.PurchaseAddress;
import com.gossamercms.mvc.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/ecommerce-purchase-addresses")
public class PurchaseAddresssController extends BaseController<PurchaseAddress, PurchaseAddressDto, PurchaseAddressRequest,  PurchaseAddressResponse, PurchaseAddressListResponse> {

    public PurchaseAddresssController(PurchaseAddresssHandler handler) {
        super(handler, PurchaseAddressListResponse.class);
    }
    @Override
        protected PurchaseAddressDto toDto(PurchaseAddressRequest request) {
            return PurchaseAddressDto.builder()
                        .purchaseId(request.getPurchaseId())
                        .addressType(request.getAddressType())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .company(request.getCompany())
                        .addressLine1(request.getAddressLine1())
                        .addressLine2(request.getAddressLine2())
                        .city(request.getCity())
                        .province(request.getProvince())
                        .postalCode(request.getPostalCode())
                        .country(request.getCountry())
                        .telephone(request.getTelephone())
                        .build();

        }

        @Override
        protected PurchaseAddressResponse toResponse(PurchaseAddressDto dto) {
            return PurchaseAddressResponse.builder()
                           .purchaseId(dto.getPurchaseId())
                           .addressType(dto.getAddressType())
                           .firstName(dto.getFirstName())
                           .lastName(dto.getLastName())
                           .company(dto.getCompany())
                           .addressLine1(dto.getAddressLine1())
                           .addressLine2(dto.getAddressLine2())
                           .city(dto.getCity())
                           .province(dto.getProvince())
                           .postalCode(dto.getPostalCode())
                           .country(dto.getCountry())
                           .telephone(dto.getTelephone())
                           .createdAt(dto.getCreatedAt())
                           .build();

        }
}
