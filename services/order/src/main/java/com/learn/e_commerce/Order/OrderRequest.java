package com.learn.e_commerce.Order;

import com.learn.e_commerce.Product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Amount must be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method must be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer id must be valid")
        @NotEmpty(message = "Customer id must be present")
        @NotBlank(message = "Customer id must be present")
        String customerId,
        @NotEmpty(message = "Chose at least one product")
        List<PurchaseRequest> products
) {

}
