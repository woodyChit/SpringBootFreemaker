package com.wd.controller;

import com.wd.entity.User;
import com.wd.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by woody on 2017/3/4.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/rest")
    public String index(){
        return "rest controller";
    }
    @RequestMapping("/save")
    public String setUser(String name,String password){
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        userService.saveUser(user);
        return user.toString();

    }
    @RequestMapping("/get")
    public String getUser(Long id){
        User user=userService.getUser(id);
        //userService.

        return user.toString();

    }
}
