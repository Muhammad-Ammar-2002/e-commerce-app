package com.learn.e_commerce.Product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    )
    {
        return ResponseEntity.ok(service.createProduct(request));
    }
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody @Valid List<ProductPurchaseRequest> request
    )
    {
        return ResponseEntity.ok(service.purchaseProduct(request));
    }


    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product_id") @Valid Integer productId
    )
    {
        return ResponseEntity.ok(service.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll()
    {
        return ResponseEntity.ok(service.findAll());
    }



}
















