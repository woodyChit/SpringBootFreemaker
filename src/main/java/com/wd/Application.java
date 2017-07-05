package com.wd;

import com.wd.init.MySetting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by woody on 2017/3/4.
 */
@SpringBootApplication
@EnableTransactionManagement
@Configuration
public class Application {
    public static void main(String[] args) {
        //System.out.println("in main");
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public MySetting setting(){
        return new MySetting();
    }
}
