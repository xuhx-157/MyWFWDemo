package cn.xuhx.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Stats;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Demo05 {

    RestHighLevelClient client;

    @Before
    public void init(){
        client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://127.0.0.1:9201")));

    }

    @After
    public void closeResource() throws IOException {
        client.close();
    }

    /**
     * 桶聚合
     * @throws IOException
     */
    @Test
    public void aggBucketData() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source()
                .query(QueryBuilders.rangeQuery("price").lte(300))      //聚合条件，限定聚合范围
                .size(0)  //设置size为0，结果中不包含文档，只包含聚合结果
                .aggregation(AggregationBuilders
                        .terms("brandAgg")  //聚合的类型、聚合名称
                        .field("brand")         //聚合字段
                        .size(10)               //聚合数量
                        .order(BucketOrder.aggregation("_count",false)));   //排序
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Aggregations aggregations = response.getAggregations();
        Terms brandAgg = aggregations.get("brandAgg");
        List<? extends Terms.Bucket> buckets = brandAgg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            String key = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            System.out.println(key + "===========" + docCount);
        }
    }


    /**
     * Metrics聚合
     * @throws IOException
     */
    @Test
    public void aggMetricsData() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source()
                .size(0)  //设置size为0，结果中不包含文档，只包含聚合结果
                .aggregation(AggregationBuilders
                        .terms("brandAgg")  //聚合的类型、聚合名称
                        .field("brand")         //聚合字段
                        .size(10)               //聚合数量
                        .order(BucketOrder.aggregation("_count",false))
                        .subAggregation(AggregationBuilders
                                .stats("scoreAgg").field("score")));   //排序
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Aggregations aggregations = response.getAggregations();
        Terms brandAgg = aggregations.get("brandAgg");
        List<? extends Terms.Bucket> buckets = brandAgg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            String key = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            Stats scoreAgg = bucket.getAggregations().get("scoreAgg");
            double avg = scoreAgg.getAvg();
            long count = scoreAgg.getCount();
            double max = scoreAgg.getMax();
            double min = scoreAgg.getMin();
            double sum = scoreAgg.getSum();
            System.out.println("count:" + count);
            System.out.println("max:" + max);
            System.out.println("min:" + min);
            System.out.println("sum:" + sum);
        }
    }
}
