package com.learn.e_commerce.Product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductResponse(Integer id,
                              String name,
                              String description,
                              Double availableQuantity,
                              BigDecimal price,
                              Integer categoryId ,
                              String categoryName,
                              String categoryDescription) {


}
