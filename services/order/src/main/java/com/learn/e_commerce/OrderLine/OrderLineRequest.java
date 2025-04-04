package com.learn.e_commerce.OrderLine;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        Integer id,
        @NotNull(message = "Product ID is required")
        @Positive(message = "Product ID must be positive")
        Integer productId,
        
        @NotNull(message = "Order ID is required")
        @Positive(message = "Order ID must be positive")
        Integer orderId,
        
        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be positive")
        Double quantity
) {
}
