package com.spartacoding.msa.payment.infrastructure.messaging;

import com.spartacoding.msa.payment.EventSerializer;
import com.spartacoding.msa.payment.application.PaymentApplicationService;
import com.spartacoding.msa.payment.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventConsumer {

    private final PaymentApplicationService paymentApplicationService;
    private final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

    @KafkaListener(topics = "order-created", groupId = "payment-group")
    public void handleOrderCreated(String message) {
        OrderCreatedEvent event = EventSerializer.deserialize(message, OrderCreatedEvent.class);
        paymentApplicationService.completePayment(event.getOrderId(), event.getTotalPrice());
    }
}
