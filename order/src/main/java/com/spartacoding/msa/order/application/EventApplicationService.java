package com.spartacoding.msa.order.application;

import com.spartacoding.msa.order.EventSerializer;
import com.spartacoding.msa.order.OrderTopic;
import com.spartacoding.msa.order.domain.events.OrderCompletedEvent;
import com.spartacoding.msa.order.domain.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventApplicationService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrderCreatedEvent(OrderCreatedEvent event) {
        kafkaTemplate.send(OrderTopic.CREATED.getTopic(), EventSerializer.serialize(event));
    }

    public void publishOrderConfirmedEvent(OrderCompletedEvent event) {
        kafkaTemplate.send(OrderTopic.COMPLETED.getTopic(), EventSerializer.serialize(event));
    }

}
