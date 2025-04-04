package com.learn.e_commerce.Kafka;

import com.learn.e_commerce.Customer.CustomerResponse;
import com.learn.e_commerce.Order.PaymentMethod;
import com.learn.e_commerce.Product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
