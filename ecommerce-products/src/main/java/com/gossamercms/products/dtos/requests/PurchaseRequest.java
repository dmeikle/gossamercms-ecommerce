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
public class PurchaseRequest {
    private String purchaseNumber;
    private UUID customerContextId;
    private String status;
    private BigDecimal totalAmount;
    private String currency;
    private Map<String, Object> metadata;

}
