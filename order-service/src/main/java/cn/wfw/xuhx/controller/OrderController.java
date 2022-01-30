package cn.wfw.xuhx.controller;

import cn.wfw.xuhx.client.UserServiceClient;
import cn.wfw.xuhx.config.MessageConfig;
import cn.wfw.xuhx.util.RequestCode;
import cn.wfw.xuhx.util.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private MessageConfig messageConfig;

    /**
     * 动态配置文件读取测试
     * @return
     */
    @RequestMapping(value = "/queryMessageSwitchStatus" ,produces = "application/json;charset=utf-8",method = RequestMethod.GET)
    public Result queryMessageSwitchStatus(){
        Boolean switchStatus = messageConfig.getSwitchStatus();
        String content = messageConfig.getContent();
        JSONObject jo = new JSONObject();
        jo.put("switchStatus",switchStatus);
        jo.put("content",content);
        return new Result(RequestCode.SUCCESS,true,jo);
    }

    /**
     * 负载均衡访问测试
     * @return
     */
    @RequestMapping(value = "/queryUser" ,produces = "application/json;charset=utf-8",method = RequestMethod.GET)
    public Result queryUser(){
        //注意：通过eureka注册中心去访问另外一个服务器，这里就不能写另外一个服务器的ip和端口，需要写另外一个服务器在eureka注册中心注册的服务名称
        //return restTemplate.getForObject("http://userservice/userService/user/queryUser", Result.class);
        //使用feignClient访问
        return userServiceClient.queryUser();
    }
}
