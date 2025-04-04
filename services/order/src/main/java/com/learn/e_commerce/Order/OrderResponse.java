package com.learn.e_commerce.Order;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String Reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
