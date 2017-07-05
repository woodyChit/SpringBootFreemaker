package com.wd.controller;

import com.wd.constructorinjection.Holder;
import com.wd.init.MySetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @RequestMapping("/index")
    public String index(Map<String,Object> map, @RequestParam String name){
        map.put("name",holder.toString() + "+"+name);
        return "index";

    }


}
