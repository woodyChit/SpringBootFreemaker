package com.wd.init;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Created by wd on 2017/6/29.
 * 演示了  bean InitializingBean生命周期。由配置文件定义bean
 */

public class MySetting implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(MySetting.class);

    @Autowired
    JedisConnectionFactory factory;
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("init...mySetting");


    }
}
