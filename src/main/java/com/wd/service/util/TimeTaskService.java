package com.wd.service.util;

import com.wd.Constants;
import com.wd.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wd on 2017/7/13.
 */
@Configurable
@EnableScheduling
@Component
public class TimeTaskService {
    @Autowired
    private RedisService redisService;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void pageView(){
        String pagePath = redisService.rightPop(Constants.REDIS_KEY_PAGEVIEW,String.class);
        if(pagePath!=null){
            System.out.println(pagePath+">>>>>>>>> length = "+ pagePath.length());
        }
    }
}
