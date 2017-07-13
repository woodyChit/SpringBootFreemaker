package com.wd.springbeanwatcher;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wd on 2017/7/13.
 */
@Service
public class BeanWatcher implements ApplicationContextAware{
    private ApplicationContext context ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public <T> List<String> getBeanNamesOfType(Class<T> tClass){
        List<String> re = new ArrayList(Arrays.asList(context.getBeanNamesForType(tClass)));
        return re;
    }
}
