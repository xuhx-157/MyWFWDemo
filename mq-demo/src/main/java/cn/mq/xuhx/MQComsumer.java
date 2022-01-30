package cn.mq.xuhx;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

@Component
public class MQComsumer {

    //注意这个队列必须存在，否则报错
    @RabbitListener(queues = "simple.queue")
    public void getMsg(String msg) throws InterruptedException {
        System.out.println("msg消费：" + msg + LocalTime.now());
    }

    //这个可以自动创建队列
    @RabbitListener(queuesToDeclare = @Queue(value = "simple.queue3"))
    public void getMsg2(Map<String,Object> map) {
        System.out.println("姓名：" + map.get("name"));
        System.out.println("年龄：" + map.get("age"));
    }

    @RabbitListener(queues = "xuhx.queue1")
    public void getMsg3(String msg) throws InterruptedException {
        System.out.println("msg3消费：" + msg + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = "xuhx.queue1")
    public void getMsg4(String msg) throws InterruptedException {
        System.out.println("msg4消费：" + msg + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "direct.exchange",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}))
    public void getMsg5(String msg){
        System.out.println("msg5消费：" + msg + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "direct.exchange",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}))
    public void getMsg6(String msg){
        System.out.println("msg6消费：" + msg + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "topic.exchange",type = ExchangeTypes.TOPIC),
            key = {"people.#"}
    ))
    public void getMsg7(String msg){
        System.out.println("msg7消费：" + msg + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "topic.exchange",type = ExchangeTypes.TOPIC),
            key = {"people.woman"}
    ))
    public void getMsg8(String msg){
        System.out.println("msg8消费：" + msg + LocalTime.now());
    }




















}
