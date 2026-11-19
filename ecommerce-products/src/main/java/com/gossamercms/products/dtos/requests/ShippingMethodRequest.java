package com.gossamercms.products.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingMethodRequest {
    private String name;
    private String description;
    private String methodType;
    private BigDecimal price;
    private String currency;
    private Boolean isActive;
    private Map<String, Object> metadata;

}
