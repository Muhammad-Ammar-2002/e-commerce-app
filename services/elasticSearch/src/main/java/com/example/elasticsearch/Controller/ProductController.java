package com.example.elasticsearch.Controller;

import com.example.elasticsearch.Entity.Product;
import com.example.elasticsearch.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/_products")

public class ProductController {

    private final ProductService productService;



    @GetMapping("/findAll")
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Integer id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/delete?id={id}")
    public String deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }
}
