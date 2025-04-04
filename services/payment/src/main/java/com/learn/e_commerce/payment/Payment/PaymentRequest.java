package com.learn.e_commerce.payment.Payment;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;


public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
