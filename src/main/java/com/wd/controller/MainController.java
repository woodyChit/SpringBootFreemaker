package com.wd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by woody on 2017/3/4.
 */
@Controller
public class MainController {
    @RequestMapping("/index")
    public String index(Map<String,Object> map, @RequestParam String name){
        map.put("name",name);
        return "index";

    }
}
