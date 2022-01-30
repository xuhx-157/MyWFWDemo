package cn.wfw.xuhx.client;

import cn.wfw.xuhx.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("userservice") //实例名称
public interface UserServiceClient {

    //路径，请求方式
    @RequestMapping(value = "/userService/user/queryUser",produces = "application/json;charset=utf-8",method = RequestMethod.GET)
    Result queryUser ();
}
