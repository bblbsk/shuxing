package com.x.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.ReindexRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Map;

public class ElasticsearchDemo {

    private static RestHighLevelClient highLevelClient;

    public static void main(String[] args) throws IOException {
        highLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1", 9200, "http")
                )
        );

//        createIndex();
        reindex();


//        insert();
//        getDocument();
//        termQuery();
//        matchQuery();
//        multiMatch();
//        booleanQuery();
//        ids();

        highLevelClient.close();
    }

    private static void reindex() throws IOException {
        ReindexRequest reindexRequest = new ReindexRequest();
        reindexRequest.setSourceIndices("shuxing1");
        reindexRequest.setDestIndex("shuxing2");
        BulkByScrollResponse response = highLevelClient.reindex(reindexRequest, RequestOptions.DEFAULT);
        System.out.println("response.getStatus() = " + response.getStatus());
        System.out.println("response.getTotal() = " + response.getTotal());
    }

    private static void createIndex() throws IOException {
        XContentBuilder contentBuilder = JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("mobile")
                        .field("type", "keyword")
                        .field("index", "true")
                        .endObject()

                        .startObject("createDate")
                        .field("type", "date")
                        .field("index", "true")
                        .endObject()

                        .startObject("corpName")
                        .field("type", "keyword")
                        .field("index", "true")
                        .endObject()

                        .startObject("smsContent")
                        .field("type", "text")
                        .field("index", "true")
                        .field("analyzer", "ik_max_word")
                        .endObject()

                        .startObject("province")
                        .field("type", "keyword")
                        .field("index", "true")
                        .endObject()

                    .endObject()
                .endObject();

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("staff");
        createIndexRequest.mapping(contentBuilder);
        CreateIndexResponse createIndexResponse = highLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    private static void ids() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder query = searchSourceBuilder.query(QueryBuilders.idsQuery().addIds("1", "2"));
        SearchRequest searchRequest = new SearchRequest("shuxing");
        searchRequest.source(query);
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        printValue(searchResponse);
    }

    private static void booleanQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
//                .must(QueryBuilders.termQuery("name", "shuxing"))
                .should(QueryBuilders.termQuery("name", "shuxing1"));
        searchSourceBuilder.query(queryBuilder);
        SearchRequest searchRequest = new SearchRequest("shuxing");
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        printValue(searchResponse);
    }

    private static void multiMatch() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder sourceBuilder = searchSourceBuilder.query(QueryBuilders.multiMatchQuery("shuxing1", "name"));
        SearchRequest searchRequest = new SearchRequest("shuxing");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        printValue(searchResponse);
    }

    private static void matchQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQuery1 = QueryBuilders.matchQuery("name", "shuxing1").analyzer("ik_max_word");
//        MatchQueryBuilder matchQuery2 = QueryBuilders.matchQuery("age", 30);
        searchSourceBuilder.query(matchQuery1);
//                .query(matchQuery2);
        SearchRequest searchRequest = new SearchRequest("shuxing");
        searchRequest.source(searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        printValue(searchResponse);
    }

    private static void termQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("shuxing");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // termQeury
        searchSourceBuilder.query(QueryBuilders.termQuery("name", "shuxing"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        printValue(searchResponse);
    }

    private static void insert() throws IOException {
        Map<String, Object> obj = Maps.newHashMap();
        obj.put("name", "shuxing1");
        IndexRequest indexRequest = new IndexRequest("shuxing", "_doc");
        indexRequest.source(JSON.toJSONString(obj), XContentType.JSON);
        IndexResponse indexResponse = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("indexResponse = " + indexResponse);
    }

    // 获取文档
    static void getDocument() throws IOException {
        GetRequest getRequest = new GetRequest("shuxing", "1");
        GetResponse documentFields = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(documentFields));
        System.out.println(documentFields.getSourceAsString());
    }

    static void printValue(SearchResponse searchResponse) {
        TotalHits totalHits = searchResponse.getHits().getTotalHits();//获取匹配的总数量
        System.out.println("总记录数:" + totalHits);
        for (SearchHit hit : searchResponse.getHits()) {
            float score = hit.getScore(); //获得分数，即匹配度
            String source = hit.getSourceAsString();
            System.out.println(source);
        }
    }
}
