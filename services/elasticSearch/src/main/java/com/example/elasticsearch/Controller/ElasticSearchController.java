package com.example.elasticsearch.Controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.elasticsearch.Entity.Product;
import com.example.elasticsearch.Service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/elasticSearch")
public class ElasticSearchController {

    private final ElasticSearchService elasticSearchService;


    @GetMapping("/matchAll")
    public String matchAllQuery() throws IOException {
        SearchResponse<Map> response= elasticSearchService.matchAllQuery();

        return response.hits().hits().toString();
    }

    @GetMapping("/matchWithIndex")
    public List<Object> matchAllProductQuery(String indexName) throws IOException {
        SearchResponse<Object> response= elasticSearchService.matchWithIndexQuery(indexName);

        List<Hit<Object>> objectHits = response.hits().hits();
        List<Object> object = new ArrayList<>();
        objectHits.forEach(hit->object.add(hit.source()));

        return object;
    }

    @GetMapping("/matchWithField/{indexName}/{fieldName}/{value}")
    public List<Object> matchWithField(@PathVariable String indexName, @PathVariable  String fieldName, @PathVariable  String value) throws IOException {
        SearchResponse<Object> response= elasticSearchService.matchWithField(indexName,fieldName,value);

        return elasticSearchService.mappingResponse(response);
    }


    @GetMapping("/autoSuggest/{indexName}/{fieldName}/{value}")
    public List<String> autoSuggestQuery(@PathVariable String indexName, @PathVariable  String fieldName, @PathVariable  String value) throws IOException {
        SearchResponse<Product> response= elasticSearchService.autoSuggestQuery(indexName,fieldName,value);

        List<Hit<Product>> objectHits = response.hits().hits();
        List<Product> productList = new ArrayList<>();

        for(Hit<Product> hit : objectHits){
            productList.add(hit.source());
        }

        List<String> fieldNameList = new ArrayList<>();

        for(Product product: productList){

            fieldNameList.add(product.getName());
        }

        return fieldNameList;
    }

    @GetMapping("/boolQuery/{indexName}/{fieldName}/{value}/{fieldName2}/{value2}")
    public List<Product> boolQuery( @PathVariable  String fieldName, @PathVariable  String value,
                                   @PathVariable  String fieldName2, @PathVariable  Integer value2) throws IOException {
        SearchResponse<Product> response= elasticSearchService.boolQuery(fieldName,value, fieldName2,value2);

        List<Hit<Product>> hitList =response.hits().hits();
        List<Product> productList = new ArrayList<>();

        for(Hit<Product> hit : hitList)
        {
            productList.add(hit.source());
        }



        return productList;
    }


}
