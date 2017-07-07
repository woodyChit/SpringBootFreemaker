package com.wd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by woody on 2017/7/6.
 */
@RestController
@RequestMapping("/session")
public class SessionController {
    @GetMapping("/getSession.json")
    public String getSession(HttpServletRequest request, String key){
        String id= request.getSession().getId();
        String value = (String)request.getSession().getAttribute(key);
        return"sessionId= "+id+" value = "+value;
    }
    @GetMapping("/setSession.json")
    public String setSessionAttr(HttpServletRequest request,String key ,String value){
        request.getSession().setAttribute(key,value);
        return "OK to set "+key+" value = "+value +"  in redis session";
    }
    @GetMapping("/hello.json")
    public String hello( ){

        return "\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\uff01 (\"Hello world!\" in Japanese)";
    }

}
