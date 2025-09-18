package com.example.elasticsearch.Util;

import co.elastic.clients.elasticsearch._types.query_dsl.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ElasticSearchUtil {


    public static Supplier<Query> supplier() {
        return ()->Query.of(q->q.matchAll(matchAllQuery()));
    }

    public static MatchAllQuery matchAllQuery() {
        return new MatchAllQuery.Builder().build();
    }

    public static Supplier<Query> supplierWithField(String fieldName, String fieldValue) {
        return ()->Query.of(q->q.match(matchAllQueryWithField(fieldName,fieldValue)));
    }

    public static MatchQuery matchAllQueryWithField(String fieldName, String fieldValue) {
        return new MatchQuery.Builder().field(fieldName).query(fieldValue).build();
    }


    public static Supplier<Query> autoSuggestSupplier(String fieldName, String fieldValue) {
        return ()->Query.of(q->q.match(matchAllAutoSuggestQueryWithField(fieldName,fieldValue)));
    }

    public static MatchQuery matchAllAutoSuggestQueryWithField(String fieldName, String fieldValue) {
        return new MatchQuery.Builder().field(fieldName).query(fieldValue).analyzer("standard").build();
    }

//    public static Supplier<Query> autoSuggestSupplier(String fieldName, String fieldValue) {
//        return ()->Query.of(q->q
//                .prefix(matchAllAutoSuggestQueryWithField(fieldName,fieldValue)));
//    }
//
//    public static PrefixQuery matchAllAutoSuggestQueryWithField(String fieldName, String fieldValue) {
//        return new PrefixQuery.Builder()
//                .field(fieldName.toLowerCase())
//                .value(fieldValue)
////                .analyzer("standard")
//                .caseInsensitive(true)
//                .build();
//    }


    public static Supplier<Query> boolQuerySupplier(String fieldName, String fieldValue, String fieldName2, Integer fieldValue2) {
        return ()->Query.of(q->q.bool(boolQuery(fieldName,fieldValue,fieldName2,fieldValue2)));
    }

    public static BoolQuery boolQuery(String fieldName, String fieldValue, String fieldName2, Integer fieldValue2) {
        return new BoolQuery.Builder().filter(filterQuery(fieldName,fieldValue)).must(mustQuery(fieldName2,fieldValue2)).build();
    }

    public static List<Query> filterQuery(String fieldName, String fieldValue) {
        List<Query> queries = new ArrayList<>();
         queries.add(Query.of(q->q.term(new TermQuery.Builder().field(fieldName).value(fieldValue).build())));
         return queries;
    }

    public static List<Query> mustQuery(String fieldName, Integer fieldValue) {
        List<Query> queries = new ArrayList<>();
     queries.add(Query.of(q->q.match(new MatchQuery.Builder().field(fieldName).query(fieldValue).build())));
    return queries;
    }

}
