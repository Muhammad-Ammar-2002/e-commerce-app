package com.learn.e_commerce.payment.Payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid PaymentRequest request
    )
    {
        return ResponseEntity.ok(service.createPayment(request));
    }
}
