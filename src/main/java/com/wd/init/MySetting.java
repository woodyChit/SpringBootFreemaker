package com.wd.init;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by wd on 2017/6/29.
 * 演示了  bean InitializingBean生命周期。由配置文件定义bean
 */

public class MySetting implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(MySetting.class);
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("init...mySetting");


    }
}
