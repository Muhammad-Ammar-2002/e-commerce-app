package com.learn.e_commerce.Product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
          Integer id,
          @NotNull(message = "Product name is required")
          String name,
          @NotNull(message = "Product description is required")
          String description,
          @NotNull(message = "Available quantity should be positive")
          Double availableQuantity,
          @NotNull(message = "Price should be positive")
          BigDecimal price,
          @NotNull(message = "Product category is required")
          Integer categoryId
) {
}
