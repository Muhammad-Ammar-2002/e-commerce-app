package com.learn.e_commerce.Customer;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Document

public class Customer {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
