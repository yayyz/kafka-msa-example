package com.spartacoding.msa.order.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCompletedEvent {
    private Long orderId;
    private String productId;
    private int quantity;
    private BigDecimal totalPrice;
    private String customerEmail;
}
