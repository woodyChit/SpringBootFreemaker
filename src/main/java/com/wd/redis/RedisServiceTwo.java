package com.wd.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wd on 2017/7/13.
 */
@Service
public class RedisServiceTwo {

    /**
     * 三个类都存在与容器中。很奇怪的是Autowired 按类型不会找到 sessionRedisTemplate ，而且是会装配到自定义的bean上去。
     */
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "sessionRedisTemplate")
    RedisTemplate sessionRedisTemplate;
    public String get(){
        return "";
    }
}
