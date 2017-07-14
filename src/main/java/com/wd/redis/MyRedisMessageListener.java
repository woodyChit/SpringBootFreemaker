package com.wd.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by wd on 2017/7/14.
 */
@Component
public class MyRedisMessageListener implements MessageListener {

    private RedisTemplate<String,Object> redisTemplate;
    private MyRedisMessageListener(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    private static final Logger logger = LoggerFactory.getLogger(MyRedisMessageListener.class);

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String channel = (String)redisTemplate.getKeySerializer().deserialize(message.getChannel());
        String content = redisTemplate.getStringSerializer().deserialize(message.getBody());
        logger.info("From {} : {}",channel,content);
    }
}
