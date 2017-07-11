package com.wd.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by woody on 2017/7/6.
 */
@Service
public class RedisService {
    //only valueOps
    //
    private final String SCORE_RANK = "RedisScoreRankZSet";

    @Autowired
    RedisTemplate<String, Object > redisTemplate;


    public void setValue(String key,Object o){
        ValueOperations<String, Object > ops = redisTemplate.opsForValue();
        ops.set(key,o,30, TimeUnit.MINUTES);
    }

    public void setValue(String key,Object o,long expire,TimeUnit unit){
        ValueOperations<String,Object> ops = redisTemplate.opsForValue();
        ops.set(key,o,expire, unit);
    }

    public <T>  T getValue(String key,Class<T> clazz){
        ValueOperations<String,Object> ops = redisTemplate.opsForValue();
        Object value = ops.get(key);
        return clazz.cast(value);
    }

    /**
     * 设置初始分
     * @param username
     * @param value
     * @return
     */
    public Boolean addNewScore(String username,double value){
        ZSetOperations<String,Object> zso = redisTemplate.opsForZSet();
        return zso.add(SCORE_RANK,username,value);
    }
    /**
     * 加分
     * @param username
     * @param value
     * @return
     */
    public Double increScore(String username,double value){
        ZSetOperations<String,Object> zso = redisTemplate.opsForZSet();
        return zso.incrementScore(SCORE_RANK,username,value);
    }

    /**
     * 得到排名
     * @param start
     * @param end
     * @return
     */
    public Map<String,Double> getRank(long start,long end){
        ZSetOperations<String,Object> zso = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<Object>> rank = zso.rangeWithScores(SCORE_RANK,start,end);
        Map<String,Double> rankMap = new LinkedHashMap<>();
        for(ZSetOperations.TypedTuple<Object> it:rank){
            rankMap.put((String)it.getValue(),it.getScore());
        }
        return rankMap;


    }
}
