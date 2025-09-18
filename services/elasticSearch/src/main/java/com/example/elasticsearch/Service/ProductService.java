package com.example.elasticsearch.Service;

import com.example.elasticsearch.Entity.Product;
import com.example.elasticsearch.Repository.ProductRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public Iterable<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product  saveProduct(Product product) {
       return productRepo.save(product);
    }



    public Product updateProduct(Product product, Integer id) {
         Product existingProduct = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

         existingProduct.setPrice(product.getPrice());
        return productRepo.save(existingProduct);
    }

    public String deleteProduct(Integer id) {
        try {
            productRepo.deleteById(id);
            return  "Product deleted";
        }catch (Exception e){
            return "Product not found";
        }
    }

}
