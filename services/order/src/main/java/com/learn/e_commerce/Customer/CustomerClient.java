package com.learn.e_commerce.Customer;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {

    @GetMapping("/{customer-id}")
//    @CircuitBreaker(name = "findAllOrders", fallbackMethod = "findAllOrdersFallback")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId );

//    default CustomerResponse findAllOrdersFallback(String customerId, Throwable t) {
//        return CustomerResponse.builder()
//                .id(customerId)
//                .firstname("Fallback")
//                .lastname("Fallback")
//                .email("Fallback")
//                .build();
//    }
}
