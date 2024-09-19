package com.spartacoding.msa.payment.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spartacoding.msa.payment.EventSerializer;
import com.spartacoding.msa.payment.events.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(EventService.class);

    public void publishPaymentCompletedEvent(PaymentCompletedEvent event) {
        kafkaTemplate.send("payment-completed", EventSerializer.serialize(event));
    }
}
