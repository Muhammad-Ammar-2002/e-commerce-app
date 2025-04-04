package com.learn.e_commerce.Kafka.Order;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        Double quantity
) {
}
