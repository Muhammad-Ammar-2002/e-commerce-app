package com.learn.e_commerce.payment.Payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
