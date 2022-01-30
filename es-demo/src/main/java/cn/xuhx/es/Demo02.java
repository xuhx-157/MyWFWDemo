package cn.xuhx.es;

import cn.hutool.db.Db;
import cn.hutool.db.handler.BeanHandler;
import cn.xuhx.entity.TbHotel;
import cn.xuhx.entity.TbHotelDoc;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Demo02 {

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
     * 插入数据
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void insertOneData() throws SQLException, IOException {
        BeanHandler<TbHotel> tbHotelBeanHandler = BeanHandler.create(TbHotel.class);
        TbHotel tbHotel = Db.use().query("select * from tb_hotel where id = ?", tbHotelBeanHandler,61083L);
        TbHotelDoc tbHotelDoc = new TbHotelDoc(tbHotel);
        IndexRequest request = new IndexRequest("hotel").id(tbHotelDoc.getId());
        request.source(JSON.toJSONString(tbHotelDoc), XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }

    /**
     * 根据ID查询数据
     * @throws IOException
     */
    @Test
    public void queryDataByID() throws IOException {
        GetRequest request = new GetRequest("hotel","61083");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        String result = response.getSourceAsString();
        TbHotelDoc tbHotelDoc = JSON.parseObject(result, TbHotelDoc.class);
        System.out.println(tbHotelDoc.toString());
    }

    /**
     * 根据ID局部更新
     * @throws IOException
     */
    @Test
    public void updateDataByID() throws IOException {
        UpdateRequest request = new UpdateRequest("hotel", "61083");
        request.doc("price",952,"starName","四钻");
        client.update(request,RequestOptions.DEFAULT);
    }

    /**
     * 根据ID删除数据
     * @throws IOException
     */
    @Test
    public void deleteDataByID() throws IOException {
        DeleteRequest request = new DeleteRequest("hotel","61083");
        client.delete(request,RequestOptions.DEFAULT);
    }
}
