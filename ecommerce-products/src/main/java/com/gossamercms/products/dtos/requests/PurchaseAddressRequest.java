package com.gossamercms.products.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseAddressRequest {
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

}
