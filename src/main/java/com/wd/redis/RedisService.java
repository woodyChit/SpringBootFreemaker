package com.wd.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Created by woody on 2017/7/6.
 */
@Service
public class RedisService {
    //only valueOps
    //

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    public void setValue(String key,Object o){
        ValueOperations<String,Object> ops = redisTemplate.opsForValue();
        ops.set(key,o);

    }

    public <T>  T getValue(String key,Class<T> clazz){
        ValueOperations<String,Object> ops = redisTemplate.opsForValue();
        Object value = ops.get(key);
        return clazz.cast(value);
    }
}
