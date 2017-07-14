package com.wd.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by wd on 2017/7/14.
 */
@Service
public class RedisPubService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public void createPub(String channelName,String msg){
        redisTemplate.convertAndSend(channelName,msg);
    }
}
