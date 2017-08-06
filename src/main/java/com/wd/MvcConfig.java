package com.wd;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Created by woody on 2017/8/5.
 */
@Configuration
//@EnableWebMvc     不能写
public class MvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        //注册自己的freemaker 解析器
        FreeMarkerViewResolver freeViewResolver = new FreeMarkerViewResolver();
        freeViewResolver.setSuffix(".ftl");
        freeViewResolver.setOrder(2);
        freeViewResolver.setContentType("text/html;charset=utf-8");
        registry.viewResolver(freeViewResolver);



//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        internalResourceViewResolver.setOrder(3);
//        internalResourceViewResolver.setViewClass(JstlView.class);      //使用jsp解析，需要javax.servlet.jsp.jstl.core   jstl  jsp 的包，懒得引了
//        internalResourceViewResolver.setContentType("text/html;charset=utf-8");
//        //internalResourceViewResolver.setPrefix("/");
//        internalResourceViewResolver.setSuffix(".jsp");
//        registry.viewResolver(internalResourceViewResolver);

    }
}
