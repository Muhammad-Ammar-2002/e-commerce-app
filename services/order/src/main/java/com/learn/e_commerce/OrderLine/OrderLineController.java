package com.learn.e_commerce.OrderLine;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-line")
public class OrderLineController {

    private final OrderLineService service;

    @GetMapping("/order/order-id")
    public ResponseEntity<List<OrderLineResponse>> findById(
            @PathVariable("order-id") @Valid Integer orderId
    )
    {
        return ResponseEntity.ok(service.findAllByOrderId(orderId));
    }
}
