package com.gossamercms.products.dtos.responses;

public class CartSummaryResponse {
    private int itemCount;
    private double subtotal;
    private double discountTotal;
    private double shippingTotal;
    private double taxTotal;
    private double grandTotal;
    private String currency;
}
