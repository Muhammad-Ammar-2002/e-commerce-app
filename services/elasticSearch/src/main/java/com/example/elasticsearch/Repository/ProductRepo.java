package com.example.elasticsearch.Repository;

import com.example.elasticsearch.Entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
public interface ProductRepo extends ElasticsearchRepository<Product,Integer> {
}
