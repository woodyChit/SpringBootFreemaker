package com.wd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by woody on 2017/3/4.
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        System.out.println("in main 0308 in new Branch");
        SpringApplication.run(Application.class,args);
    }

//    @Bean
//    public DataSource dataPool(){
//        //DataSource dataSource=new
//    }
}
