package org.example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TermQueryTest {


    /**
     * post : localhost:9200/index_blog1/index_blog1/docid_00121
     * {"name":"abc1","age":31 ,"content":"this is a doc  abc1"}
     *
     * post : localhost:9200/index_blog1/index_blog1/docid_0001
     * {"name":"abc","age":22 ,"content":"this is a doc  abc"}
     *
     */


    private RestHighLevelClient client ;

    @Before
    public void setUp() throws Exception {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

    }

    @After
    public void tearDown() throws Exception {
        client.close();
    }

    /**
     * name = "abc1"
     * @throws IOException
     */
    @Test
    public void queryTerm() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("name", "abc1"));

        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.indices("index_blog1");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }


    }


    /**
     * name in ("abc1","abc")
     * @throws IOException
     */
    @Test
    public void queryTerms() throws  IOException{
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.termsQuery("name", "abc1","abc"));

        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.indices("index_blog1");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }


    }

    /**
     * 大于小于 查询
     *
     */
    @Test
    public void queryGtAndLtTerm()throws  IOException{
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.rangeQuery("age")
//                .from(22).includeLower(false)  //  age > 22
                .from(22).includeLower(true)  //  age >= 22
//                .from(31).includeLower(false)  //  age < 31
//                .from(31).includeLower(true)  //  age >= 31
        );

        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.indices("index_blog1");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }


    }



    /**
     * 范围查询
     * @throws IOException
     */
    @Test
    public void queryRangeTerm()throws  IOException{
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.rangeQuery("age")
                .from(22)
                .to(31)
                .includeLower(true)
                .includeUpper(false)
        );

        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.indices("index_blog1");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }


    }


    /**
     * AND 条件查询
     * name = "abc" and age=22
     *
     */
    @Test
    public  void queryAndBool() throws  IOException{
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.boolQuery()
                .must( QueryBuilders.termQuery("name","abc"))
                .must(QueryBuilders.termQuery("age",24))
        );

        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.indices("index_blog1");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }

    }


    /**
     * OR条件查询
     * name = "abc" or age=24
     *
     */
    @Test
    public  void queryOrBool() throws  IOException{
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.boolQuery()
                .should( QueryBuilders.termQuery("name","abc"))
                .should((QueryBuilders.termQuery("age",24)))
        );

        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.indices("index_blog1");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }

    }

}
