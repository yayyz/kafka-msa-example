package com.spartacoding.msa.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    private String productId;
    private int quantity;
    private BigDecimal totalPrice;
    private String status;
    private String customerEmail;
}
