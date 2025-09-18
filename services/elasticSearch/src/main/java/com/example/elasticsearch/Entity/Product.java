package com.example.elasticsearch.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
}
