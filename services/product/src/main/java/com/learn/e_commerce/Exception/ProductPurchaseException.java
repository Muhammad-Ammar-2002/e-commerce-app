package com.learn.e_commerce.Exception;

public class ProductPurchaseException extends RuntimeException{
    public ProductPurchaseException(String message) {
        super(message);
    }
}
