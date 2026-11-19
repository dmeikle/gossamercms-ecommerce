package com.gossamercms.products.dtos;

import com.gossamercms.products.models.PurchaseAddress;
import com.gossamercms.mvc.data.DtoWithId;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseAddressDto implements DtoWithId{
    private UUID id;
    private UUID purchaseId;
    private String addressType;
    private String firstName;
    private String lastName;
    private String company;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String postalCode;
    private String country;
    private String telephone;
    private Instant createdAt;

    public PurchaseAddress toEntity() {
        return PurchaseAddress.builder()
            .id(id)
            .purchaseId(purchaseId)
            .addressType(addressType)
            .firstName(firstName)
            .lastName(lastName)
            .company(company)
            .addressLine1(addressLine1)
            .addressLine2(addressLine2)
            .city(city)
            .province(province)
            .postalCode(postalCode)
            .country(country)
            .telephone(telephone)
            .createdAt(createdAt)
            .build();
    }
}
