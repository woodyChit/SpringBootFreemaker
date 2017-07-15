package com.wd.controller;

import com.wd.entity.Order;
import com.wd.entity.RedisUser;
import com.wd.entity.User;
import com.wd.redis.RedisPubService;
import com.wd.redis.RedisService;
import com.wd.redis.RedisServiceTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by woody on 2017/7/6.
 */
@RestController
@RequestMapping("/redis")
public class RedisJsonController {
    @Autowired
    RedisService redisService;

    @Autowired
    RedisPubService redisPubService;
    @Autowired
    RedisServiceTwo redisServiceTwo;

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

    @PostMapping("/rdScore.json")
    public RedisService.RankTurple setNewScore(String name){
        RedisService.RankTurple info = redisService.genRandomScore(name);
        return info;
    }

    @PostMapping("pub.json")
    public String pub(String cname,String msg){
        redisPubService.createPub(cname,msg);
        return "OK";
    }
    @GetMapping("/setRedisUser.json")
    public String setRedisUser(Integer id,String name,String pwd){
        RedisUser user = new RedisUser(id,name,pwd);

        redisService.setRedisUser(user);
        return "OK set "+ user.toString();
    }


    @GetMapping("/delRedisUser.json")
    public String delRedisUser(Integer id){

        redisService.delRedisUser(id);
        return "OK del by id ";
    }

    @GetMapping("/getRedisUser.json")
    public String getRedisUser(Integer id,String newPwd ){

        RedisUser u = redisService.getRedisUser(id);
        System.out.println(u);
        if(u!=null && newPwd!=null){
            u.setPassword(newPwd);
            redisService.setRedisUser(u);
        }
        return "modified "+u.toString();
    }



}
