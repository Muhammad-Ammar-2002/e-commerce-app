package com.learn.e_commerce.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,
         @NotNull(message = "Customer first name is required")
         String firstname,
         @NotNull(message = "Customer last name is required")
         String lastname,
         @Email(message = "Customer email is not valid")
         String email,
         Address address
) {


}
