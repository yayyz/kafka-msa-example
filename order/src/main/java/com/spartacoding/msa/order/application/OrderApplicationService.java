package com.spartacoding.msa.order.application;

import com.spartacoding.msa.order.domain.Order;
import com.spartacoding.msa.order.domain.OrderRepository;
import com.spartacoding.msa.order.domain.events.OrderCompletedEvent;
import com.spartacoding.msa.order.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderApplicationService {
    private final OrderRepository orderRepository;
    private final EventApplicationService eventApplicationService;

    @Transactional
    public OrderResponseDto createOrder(String productId, int quantity, String customerEmail, BigDecimal totalPrice) {
        Order order = new Order(productId, quantity, customerEmail, totalPrice);
        orderRepository.save(order);

        // TODO OrderCreatedEvent 발행
        eventApplicationService.publishOrderCreatedEvent(order.createOrderCreatedEvent());
        return convertToDto(order);
    }

    @Transactional
    public void completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        OrderCompletedEvent event = order.completed();
        orderRepository.save(order);

        // 주문 완료 이벤트 발행
        eventApplicationService.publishOrderConfirmedEvent(event);
    }

    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private OrderResponseDto convertToDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getProductId(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getStatus().name(),
                order.getCustomerEmail()
        );
    }
}
