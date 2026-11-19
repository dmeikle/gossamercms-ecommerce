package com.gossamercms.products.dtos.requests;

import com.gossamercms.products.dtos.PurchaseAddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {
    private String customerEmail;
    private String customerFirstName;
    private String customerLastName;
    private List<PurchaseAddressRequest> addresses;
    private String paymentMethod;

    public String getCustomerName() {
        String firstName = customerFirstName != null ? customerFirstName : "";
        String lastName = customerLastName != null ? customerLastName : "";
        return (firstName + " " + lastName).trim();
    }

    /**
     * Retrieves the purchase address of the specified type from the list of purchase addresses.
     *
     * @param type The type of the purchase address to retrieve (e.g., "billing", "shipping").
     * @return The corresponding PurchaseAddressDto if found; otherwise, null.
     */
    public PurchaseAddressDto getPurchaseAddress(String type) {
        if (addresses == null || type == null) {
            return null;
        }

        for (PurchaseAddressRequest address : addresses) {
            if (address != null && type.equals(address.getAddressType())) {
                return PurchaseAddressDto.builder()
                        .addressType(address.getAddressType())
                        .firstName(address.getFirstName())
                        .lastName(address.getLastName())
                        .addressLine1(address.getAddressLine1())
                        .addressLine2(address.getAddressLine2())
                        .city(address.getCity())
                        .province(address.getProvince())
                        .postalCode(address.getPostalCode())
                        .country(address.getCountry())
                        .build();
            }
        }

        return null;
    }
}
