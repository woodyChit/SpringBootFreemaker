package com.wd.service.util;

import com.wd.Constants;
import com.wd.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(TimeTaskService.class);
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void pageView(){
        String pagePath = redisService.rightPop(Constants.REDIS_KEY_PAGEVIEW,String.class);
        if(pagePath!=null){
            logger.info("{}>>>>>>>>> length = {}",pagePath, pagePath.length());
        }
    }
}
