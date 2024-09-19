package com.spartacoding.msa.payment.events;

import com.spartacoding.msa.payment.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCompletedEvent {
    private Long paymentId;
    private Long orderId;
    private PaymentStatus status;
}