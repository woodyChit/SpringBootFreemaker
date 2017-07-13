package com.wd.controller;

import com.wd.springbeanwatcher.BeanWatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wd on 2017/7/13.
 */
@RestController
public class BeanWatcherJsonController {

    @Autowired
    BeanWatcher watcher;

    @GetMapping("/bean/get.json")
    public String get(String a){
        return watcher.getBeanNamesOfType(RedisTemplate.class).toString();
    }
}
