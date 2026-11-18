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
public class ProductResponse {
    private String sku;
    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private String currency;
    private String status;
    private Map<String, Object> metadata;
    private String thumbnailUrl;
    private Instant createdAt;

}