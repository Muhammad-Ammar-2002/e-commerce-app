package com.learn.e_commerce.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        @Positive(message = "Product ID must be positive")
        Integer productId,
        
        @NotNull(message = "Quantity is mandatory")
        @Positive(message = "Quantity must be positive")
        Double quantity
) {
}
