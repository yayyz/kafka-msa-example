package com.spartacoding.msa.order.domain;

import com.spartacoding.msa.order.domain.events.OrderCompletedEvent;
import com.spartacoding.msa.order.domain.events.OrderCreatedEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String customerEmail;
    private BigDecimal totalPrice;

    public Order(String productId, int quantity, String customerEmail, BigDecimal totalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.status = OrderStatus.CREATED;
        this.customerEmail = customerEmail;
        this.totalPrice = totalPrice;
    }

    public OrderCreatedEvent createOrderCreatedEvent() {
        return new OrderCreatedEvent(this.id, this.productId, this.quantity, this.totalPrice); // TODO
    }

    public OrderCompletedEvent completed() {
        this.status = OrderStatus.COMPLETED;
        return new OrderCompletedEvent(this.id, this.productId, this.quantity, this.totalPrice, this.customerEmail);
    }

    public void cancel() {
        this.status = OrderStatus.CANCELLED;
    }
}
