package com.wd.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by woody on 2017/3/4.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @RequestMapping("/rest")
    public String index(String param){
        return "rest controller: param "+param;
    }
}
