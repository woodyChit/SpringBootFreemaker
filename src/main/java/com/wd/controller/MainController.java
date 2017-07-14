package com.wd.controller;

import com.wd.constructorinjection.Holder;
import com.wd.init.MySetting;
import com.wd.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
    @RequestMapping("/index.html")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.addObject("name",holder.toString()  );
        modelAndView.setViewName("index");
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
    @GetMapping("/score.html")
    public ModelAndView score(ModelAndView modelAndView,String name, Double score,Long myrank){
        Map<String,Double> rank = redisService.getRank(0,9);
        modelAndView.addObject("rank",rank);
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


}
