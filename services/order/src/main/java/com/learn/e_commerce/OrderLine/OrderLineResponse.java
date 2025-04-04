package com.learn.e_commerce.OrderLine;

public record OrderLineResponse(
        Integer id,
        Integer productId,
        Double quantity
) {
}
