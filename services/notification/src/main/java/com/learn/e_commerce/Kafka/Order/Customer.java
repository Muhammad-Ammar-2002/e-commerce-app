package com.learn.e_commerce.Kafka.Order;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
