package com.gossamercms.products.dtos.responses;

import com.gossamercms.products.dtos.ProductDto;
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
public class CartItemResponse {
    private UUID id;
    private UUID cartId;
    private UUID productId;
    private Integer quantity;
    private Instant createdAt;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    private String taxCode;

    //using dto because we need the id also
    private ProductDto product;

}
