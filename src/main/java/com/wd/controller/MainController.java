package com.wd.controller;

import com.wd.constructorinjection.Holder;
import com.wd.init.MySetting;
import com.wd.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

/**
 * Created by woody on 2017/3/4.
 */
@Controller
public class MainController {
    @Autowired
    private Holder holder;
    @Autowired
    private MySetting setting;

    @Autowired
    RedisService redisService;
    @RequestMapping(path = { "/index.html", "/" , "" })
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.addObject("name",holder.toString()  );
        modelAndView.setViewName("index");          //如果指定了view 的名字，则按照viewResolver 的order 顺序解析。
        return modelAndView;
    }

    /**
     * 排行榜小demo
     * @param modelAndView
     * @param name
     * @param score
     * @param myrank
     * @return
     */
    @GetMapping("/score.html")                      //如果未指定view的名字，则按照路径名字进行查找
    public ModelAndView score3(ModelAndView modelAndView,String name, Double score,Long myrank){
        Map<String,Double> rank = redisService.getRank(0,9);
        modelAndView.addObject("rank",rank);
        modelAndView.addObject("total",redisService.getLastRank());
        if(StringUtils.isEmpty(name)){
            modelAndView.addObject("isNew",true);
        }else{
            modelAndView.addObject("isNew",false);
            modelAndView.addObject("name",name);
            modelAndView.addObject("score",score);
            modelAndView.addObject("myrank",myrank);
        }
        return modelAndView;
    }

    @GetMapping("/score2.html")             //展示了 第二位的viewResolver
    public ModelAndView score2(ModelAndView modelAndView,String name, Double score,Long myrank){
        Map<String,Double> rank = redisService.getRank(0,9);
        modelAndView.addObject("rank",rank);
        modelAndView.addObject("total",redisService.getLastRank());
        if(StringUtils.isEmpty(name)){
            modelAndView.addObject("isNew",true);
        }else{
            modelAndView.addObject("isNew",false);
            modelAndView.addObject("name",name);
            modelAndView.addObject("score",score);
            modelAndView.addObject("myrank",myrank);
        }
        modelAndView.setViewName("scoreFtl");       //按顺序找不到  scoreFtl.html  ，继续找  scoreFtl.ftl  找到了。则用 第二个渲染
        return modelAndView;
    }

    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @PostMapping("/login.html")
    public String loginPost(String username,String password){
        System.out.println("username "+username +"pass "+password);
        return "login";
    }
}
