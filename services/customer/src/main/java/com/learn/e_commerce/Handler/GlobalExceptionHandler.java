package com.learn.e_commerce.Handler;

import com.learn.e_commerce.Exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handel(CustomerNotFoundException exp)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMsg());
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
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }
}
