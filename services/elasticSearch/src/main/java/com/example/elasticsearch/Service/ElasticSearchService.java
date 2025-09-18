package com.example.elasticsearch.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.elasticsearch.Entity.Product;
import com.example.elasticsearch.Util.ElasticSearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class ElasticSearchService {

    private final ElasticsearchClient elasticSearchClient;

    public SearchResponse<Map> matchAllQuery() throws IOException
    {

        Supplier<Query> supplier=ElasticSearchUtil.supplier();

        System.out.println(supplier.get().toString());

        return elasticSearchClient.search(q->q.query(supplier.get()),Map.class);


    }
    public SearchResponse<Object> matchWithIndexQuery(String indexName) throws IOException
    {

        Supplier<Query> supplier=ElasticSearchUtil.supplier();

        System.out.println(supplier.get().toString());

        return elasticSearchClient.search(q->q.index(indexName).query(supplier.get()),Object.class);


    }

    public SearchResponse<Object> matchWithField(String indexName,String fieldName,String value) throws IOException
    {

        Supplier<Query> supplier=ElasticSearchUtil.supplierWithField(fieldName,value);

        System.out.println(supplier.get().toString());

        return elasticSearchClient.search(q->q.index(indexName).query(supplier.get()),Object.class);

    }

    public SearchResponse<Product> autoSuggestQuery(String indexName,String fieldName,String value) throws IOException
    {

        Supplier<Query> supplier=ElasticSearchUtil.autoSuggestSupplier(fieldName,value);

        System.out.println(supplier.get().toString());

        return elasticSearchClient.search(q->q.index(indexName).query(supplier.get()),Product.class);
    }

    public SearchResponse<Product> boolQuery(String fieldName,String value,String fieldName2,Integer value2) throws IOException
    {
        Supplier<Query> supplier=ElasticSearchUtil.boolQuerySupplier(fieldName,value,fieldName2,value2);
        System.out.println(supplier.get().toString());
        return elasticSearchClient.search(q->q.index("products").query(supplier.get()),Product.class);
    }







    public List<Object> mappingResponse(SearchResponse<Object> response) throws IOException
    {
        List<Hit<Object>> objectHits = response.hits().hits();
        List<Object> object = new ArrayList<>();
        objectHits.forEach(hit->object.add(hit.source()));

        return object;
    }

}
