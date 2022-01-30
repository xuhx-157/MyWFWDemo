package cn.wfw.xuhx.controller;

import cn.wfw.xuhx.util.RequestCode;
import cn.wfw.xuhx.util.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/queryUser" , produces = "application/json;charset=utf-8" ,method = RequestMethod.GET)
    public Result queryUser(@RequestHeader("Truth") String truth){
        JSONObject data = new JSONObject();
        data.put("name","张三");
        data.put("age",23);
        data.put("hobby","篮球");
        data.put("truth",truth);
        System.out.println("=====查询用户=====");
        return new Result(RequestCode.SUCCESS,true,data);
    }
}
