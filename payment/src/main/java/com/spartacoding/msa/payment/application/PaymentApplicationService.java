package com.spartacoding.msa.payment.application;

import com.spartacoding.msa.payment.domain.Payment;
import com.spartacoding.msa.payment.domain.PaymentRepository;
import com.spartacoding.msa.payment.events.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentApplicationService {

    Logger logger = LoggerFactory.getLogger(PaymentApplicationService.class);
    private final PaymentRepository paymentRepository;
    private final EventService eventService;

    @Transactional
    public void completePayment(Long orderId, BigDecimal totalPrice) {
        Payment payment = new Payment(orderId, totalPrice);
        payment = paymentRepository.save(payment);

        // 결제 성공 후 상태를 업데이트
        payment.complete();
        paymentRepository.save(payment);

        // PaymentCompletedEvent 발행
        PaymentCompletedEvent paymentEvent = new PaymentCompletedEvent(payment.getId(), payment.getOrderId(), payment.getStatus());
        eventService.publishPaymentCompletedEvent(paymentEvent);
    }
}
