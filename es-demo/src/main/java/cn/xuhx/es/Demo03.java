package cn.xuhx.es;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.xuhx.entity.TbHotel;
import cn.xuhx.entity.TbHotelDoc;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Demo03 {

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
     * 批量导入数据
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void batchUpdateData() throws SQLException, IOException {
        List<TbHotel> tbHotelList = Db.use().findAll(Entity.create("tb_hotel"), TbHotel.class);
        BulkRequest request = new BulkRequest();
        for(TbHotel tbHotel : tbHotelList){
            TbHotelDoc tbHotelDoc = new TbHotelDoc(tbHotel);
            request.add(new IndexRequest("hotel").id(tbHotelDoc.getId()).source(JSON.toJSONString(tbHotelDoc), XContentType.JSON));
        }
        client.bulk(request, RequestOptions.DEFAULT);
    }
}
