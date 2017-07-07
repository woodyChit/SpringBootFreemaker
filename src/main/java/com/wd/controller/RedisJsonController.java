package com.wd.controller;

import com.wd.entity.User;
import com.wd.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by woody on 2017/7/6.
 */
@RestController
@RequestMapping("/redis")
public class RedisJsonController {
    @Autowired
    RedisService redisService;
    @GetMapping("/setUser.json")
    public String setValue(String key,String name,String pwd){
        User u = new User();
        u.setName(name);
        u.setPassword(pwd);
        redisService.setValue(key,u);
        return "OK :" + u.toString();
    }
    @GetMapping("/getUser.json")
    public String getValue(String key){
        User u = redisService.getValue(key,User.class);
        return "OK get "+u.toString();
    }
}
