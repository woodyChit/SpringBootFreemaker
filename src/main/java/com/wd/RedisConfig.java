package com.wd;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Created by wd on 2017/7/11.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return  (target,method,args)->{
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName()).append(method.getName());
            for(int i=0;i<args.length;i++){
                sb.append(args[i].toString());
            }
            return sb.toString();
        };
    }
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(10*60);  //10mins

        return redisCacheManager;
    }

    @Bean
    public CacheResolver resolver( RedisCacheManager cacheManager){
        return new SimpleCacheResolver(cacheManager);
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        setRedisTemplateValueSerializer(redisTemplate);
        return redisTemplate;

    }

    private void setRedisTemplateValueSerializer(RedisTemplate template){
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper mapper= new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        template.setKeySerializer(template.getStringSerializer());
        template.setValueSerializer(serializer);
    }
}
