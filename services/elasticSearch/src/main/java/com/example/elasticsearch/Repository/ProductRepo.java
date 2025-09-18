package com.example.elasticsearch.Repository;

import com.example.elasticsearch.Entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepo extends ElasticsearchRepository<Product,Integer> {
}
