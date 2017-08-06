package com.wd;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd.init.MySetting;
import com.wd.mymapper.MapperProxyFactory;
import com.wd.redis.MyRedisMessageListener;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;


/**
 * Created by woody on 2017/3/4.
 */
@SpringBootApplication
@Configuration
@EnableRedisHttpSession(redisNamespace = "wdmain_httpsession")       //httpsession -> redis
@ComponentScan(basePackages = "com.wd")
@EnableRedisRepositories                                              //redis Repository注解
@ServletComponentScan(basePackages = "com.wd")                      //注册@WebServlet @WebFilter等
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public MySetting setting(){
        return new MySetting();
    }

    /**
     * 需要注入：RedisConnectionFactory
     * 需要 MessageListenerAdapter( MessageListener 自己实现的)
     * 需要 ChannelTopic(String)
     * @param redisConnectionFactory
     * @param myRedisMessageListener
     * @return
     */
    //===================  Redis PUB  /   SUB  =================
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,MyRedisMessageListener myRedisMessageListener){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(new MessageListenerAdapter(myRedisMessageListener),new ChannelTopic(Constants.REDIS_CHANNEL));
        return container;
    }

    @Bean
    public CookieSerializer cookieSerializer(){
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName(Constants.COOKIE_SESSION_ID);
        return cookieSerializer;
    }

    @Bean
    public MapperProxyFactory mapperProxyFactory(SqlSessionFactory sqlSessionFactory){
        return new MapperProxyFactory(sqlSessionFactory);
    }

}
