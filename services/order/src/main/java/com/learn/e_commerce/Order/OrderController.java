package com.learn.e_commerce.Order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Order")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest request
    )
    {
        return ResponseEntity.ok(service.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll()
    {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/Order-id")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("Order-id") @Valid Integer orderId
    )
    {
        return ResponseEntity.ok(service.findById(orderId));
    }
}
