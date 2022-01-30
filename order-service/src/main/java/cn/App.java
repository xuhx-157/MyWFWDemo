package cn;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients //开启feignClient
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @Bean
    @LoadBalanced   //开启负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    //设置全局负载均衡策略
//    @Bean
//    public IRule randomRule(){
//        return new RandomRule();
//    }
}
