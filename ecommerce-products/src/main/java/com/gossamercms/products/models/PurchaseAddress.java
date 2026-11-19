package com.gossamercms.products.models;

import com.gossamercms.products.dtos.PurchaseAddressDto;
import com.gossamercms.mvc.models.BaseModel;
import com.gossamercms.mvc.models.ModelMeta;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseAddress implements BaseModel{
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

    public PurchaseAddressDto toDto() {
        return PurchaseAddressDto.builder()
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


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_purchase_addresses")
    .column("id", UUID.class)
    .column("purchaseId", UUID.class)
    .column("addressType", String.class, 20)
    .column("firstName", String.class, 100)
    .column("lastName", String.class, 100)
    .column("company", String.class, 255)
    .column("addressLine1", String.class, 255)
    .column("addressLine2", String.class, 255)
    .column("city", String.class, 100)
    .column("province", String.class, 100)
    .column("postalCode", String.class, 20)
    .column("country", String.class, 2)
    .column("telephone", String.class, 30)
    .column("createdAt", Instant.class)
    .defaultSort("id desc")
    .build();



    @Override
    public ModelMeta meta() {
        return META;
    }

    @Override
    public ModelMeta metaOf() {
        return BaseModel.super.metaOf();
    }

}
