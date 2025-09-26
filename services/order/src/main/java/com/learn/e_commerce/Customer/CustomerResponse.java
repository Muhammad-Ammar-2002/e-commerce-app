package com.learn.e_commerce.Customer;

import lombok.Builder;

@Builder
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email

) {
}
