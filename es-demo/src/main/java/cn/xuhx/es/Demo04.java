package cn.xuhx.es;

import cn.hutool.core.map.MapUtil;
import cn.xuhx.entity.TbHotelDoc;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class Demo04 {

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
     * 查询所有
     */
    @Test
    public void matchAll() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchAllQuery());
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        System.out.println(total);
        SearchHit[] data = hits.getHits();
        for (SearchHit hit : data) {
            String str = hit.getSourceAsString();
            TbHotelDoc tbHotelDoc = JSON.parseObject(str, TbHotelDoc.class);
            System.out.println(tbHotelDoc.toString());
        }
    }

    /**
     * 查询1个字段
     * @throws IOException
     */
    @Test
    public void matchOneField() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchQuery("all","如家"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        System.out.println(total);
        SearchHit[] data = hits.getHits();
        for (SearchHit hit : data) {
            String str = hit.getSourceAsString();
            TbHotelDoc tbHotelDoc = JSON.parseObject(str, TbHotelDoc.class);
            System.out.println(tbHotelDoc.toString());
        }
    }

    /**
     * 查询多个字段
     * @throws IOException
     */
    @Test
    public void matchMoreField() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.multiMatchQuery("如家","address","name","business"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        System.out.println(total);
        SearchHit[] data = hits.getHits();
        for (SearchHit hit : data) {
            String str = hit.getSourceAsString();
            TbHotelDoc tbHotelDoc = JSON.parseObject(str, TbHotelDoc.class);
            System.out.println(tbHotelDoc.toString());
        }
    }

    /**
     * 组合查询
     * @throws IOException
     */
    @Test
    public void boolQuery() throws IOException {
        int page = 2;
        int limit = 10;
        int from = (page - 1) * 10;
        SearchRequest request = new SearchRequest("hotel");
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //组合查询
        boolQuery.must(QueryBuilders.termQuery("all","如家"));
        boolQuery.must(QueryBuilders.rangeQuery("price").lte(250));
        request.source().query(boolQuery)
                //排序
                .sort(SortBuilders.geoDistanceSort("location", new GeoPoint(31.21,121.5)).unit(DistanceUnit.KILOMETERS).order(SortOrder.ASC))
                .sort("price", SortOrder.ASC)
                .sort("score", SortOrder.DESC)
                //分页
                .from(from).size(limit)
                //高亮
                .highlighter(new HighlightBuilder().field("name").requireFieldMatch(false));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        //获取查询数量
        long total = hits.getTotalHits().value;
        System.out.println(total);
        //获取查询数据
        SearchHit[] data = hits.getHits();
        for (SearchHit hit : data) {
            String str = hit.getSourceAsString();
            TbHotelDoc tbHotelDoc = JSON.parseObject(str, TbHotelDoc.class);


            //获取高亮结果
            Map<String, HighlightField> highlightMaps = hit.getHighlightFields();
            if(MapUtil.isNotEmpty(highlightMaps)){
                HighlightField name = highlightMaps.get("name");
                String s = name.getFragments()[0].string();
                tbHotelDoc.setName(s);
            }
            System.out.println(tbHotelDoc.toString());
        }
    }
}
