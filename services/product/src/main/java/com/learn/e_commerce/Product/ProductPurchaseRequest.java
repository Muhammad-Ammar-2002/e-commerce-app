package com.learn.e_commerce.Product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory.")
        Integer productId,
        @NotNull(message = "Quantity is mandatory.")
        Double quantity
) {
}
