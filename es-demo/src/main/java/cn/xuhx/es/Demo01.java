package cn.xuhx.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Demo01 {

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
     * 创建索引库
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        //创建reuqest对象
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        //请求参数
        request.source("{\n" +
                "  \"mappings\": {\n" +
                "    \"properties\": {\n" +
                "      \"id\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"name\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_smart\",\n" +
                "        \"copy_to\": \"all\"\n" +
                "      },\n" +
                "      \"address\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_smart\"\n" +
                "      },\n" +
                "      \"price\": {\n" +
                "        \"type\": \"integer\"\n" +
                "      },\n" +
                "      \"score\": {\n" +
                "        \"type\": \"integer\"\n" +
                "      },\n" +
                "      \"brand\": {\n" +
                "        \"type\": \"keyword\",\n" +
                "        \"copy_to\": \"all\"\n" +
                "      },\n" +
                "      \"city\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"star_name\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"business\": {\n" +
                "        \"type\": \"keyword\",\n" +
                "        \"copy_to\": \"all\"\n" +
                "      },\n" +
                "      \"location\": {\n" +
                "        \"type\": \"geo_point\"\n" +
                "      },\n" +
                "      \"pic\": {\n" +
                "        \"type\": \"keyword\",\n" +
                "        \"index\": false\n" +
                "      },\n" +
                "      \"all\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_smart\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}", XContentType.JSON);
        //发起请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    /**
     * 删除索引
     * @throws IOException
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("hotel");
        client.indices().delete(request,RequestOptions.DEFAULT);
    }

    /**
     * 判断索引库是否存在
     * @throws IOException
     */
    @Test
    public void exisitIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("hotel");
        boolean flag = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(flag ? "存在" : "不存在");
    }
}
