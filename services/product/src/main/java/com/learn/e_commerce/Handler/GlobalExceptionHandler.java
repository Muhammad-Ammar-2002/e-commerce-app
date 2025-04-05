package com.learn.e_commerce.Handler;

import com.learn.e_commerce.Exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handel(ProductPurchaseException exp)
    {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exp.getMessage());
    }
        @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handel(EntityNotFoundException exp)
    {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exp.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handel(MethodArgumentNotValidException exp)
    {
        var errors=new HashMap<String,String>();
          exp.getBindingResult().getAllErrors()
                  .forEach(error -> {
                      var filedName =((FieldError)error).getField();
                      var errorMsg = error.getDefaultMessage();
                      errors.put(filedName,errorMsg);
                  });
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }
}
