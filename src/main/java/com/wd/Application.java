package com.wd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by woody on 2017/3/4.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("in main");
        SpringApplication.run(Application.class,args);
    }
}
