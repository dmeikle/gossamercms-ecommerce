package com.gossamercms.products.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
   private UUID id;
   private String currency;
   private CartSummaryResponse summary;
   private List<CartItemResponse> items;
}
/*

{
  "id": "cart-uuid",
  "currency": "CAD",

  "items": [
    {
      "id": "cart-item-uuid",
      "productId": "product-uuid",
      "quantity": 2,
      "unitPrice": 49.99,
      "subtotal": 99.98,
      "product": {
        "name": "Wireless Headphones",
        "slug": "wireless-headphones",
        "sku": "WH-001",
        "image": {
          "url": "/media/products/headphones.jpg",
          "alt": "Wireless Headphones"
        },
        "isAvailable": true
      }
    }
  ],

  "summary": {
    "itemCount": 2,
    "subtotal": 99.98,
    "discountTotal": 0.00,
    "shippingTotal": 0.00,
    "taxTotal": 0.00,
    "grandTotal": 99.98,
    "currency": "CAD"
  },

  "createdAt": "2026-11-18T15:00:00Z",
  "updatedAt": "2026-11-18T15:00:00Z"
}

 */