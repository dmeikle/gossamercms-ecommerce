package com.gossamercms.products.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingMethodResponse {
    private String name;
    private String description;
    private String methodType;
    private BigDecimal price;
    private String currency;
    private Boolean isActive;
    private Map<String, Object> metadata;
    private Instant createdAt;

}