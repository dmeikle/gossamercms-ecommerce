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
public class PurchaseListResponse {
    private String purchaseNumber;
    private UUID customerContextId;
    private String status;
    private BigDecimal totalAmount;
    private String currency;
    private Map<String, Object> metadata;
    private Instant createdAt;

}