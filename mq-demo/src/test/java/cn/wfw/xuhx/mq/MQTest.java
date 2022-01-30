package cn.wfw.xuhx.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMsg(){
        String queueName = "simple.queue";
        String msg = "Hello rabbitMQ!";
        rabbitTemplate.convertAndSend(queueName,msg);
    }

    @Test
    public void sendMoreMsg() throws InterruptedException {
        String queueName = "simple.queue3";
        Map<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",23);
        rabbitTemplate.convertAndSend(queueName,map);
    }

    @Test
    public void sendMsgToExchange(){
        String exchange = "xuhx.fanout";
        String msg = "Hello rabbitMQ!";
        rabbitTemplate.convertAndSend(exchange,"",msg);
    }

    @Test
    public void sendMsgToDirectExchange(){
        String exchange = "direct.exchange";
        String msg = "Hello rabbitMQ!";
        rabbitTemplate.convertAndSend(exchange,"red",msg);
    }

    @Test
    public void sendMsgToTopicExchange(){
        String exchange = "topic.exchange";
        String msg = "怀孕妇女不需要接种疫苗";
        rabbitTemplate.convertAndSend(exchange,"people.woman",msg);
    }
}
