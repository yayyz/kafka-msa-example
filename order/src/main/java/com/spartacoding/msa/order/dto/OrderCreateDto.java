package com.spartacoding.msa.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreateDto {
    private String productId;
    private int quantity;
    private String customerEmail;
    private BigDecimal totalPrice;
}
