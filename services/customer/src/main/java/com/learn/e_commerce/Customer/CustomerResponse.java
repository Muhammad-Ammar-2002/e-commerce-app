package com.learn.e_commerce.Customer;


public record CustomerResponse (String id,
                                String firstname,
                                String lastname,
                                String email,
                                Address address){
}
