package com.learn.e_commerce.Payment;


import com.learn.e_commerce.Customer.CustomerResponse;
import com.learn.e_commerce.Order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
