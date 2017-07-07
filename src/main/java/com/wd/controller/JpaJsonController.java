package com.wd.controller;

import com.wd.entity.Order;
import com.wd.entity.User;
import com.wd.service.OrderServiceImpl;
import com.wd.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by woody on 2017/3/4.
 */
@RestController
@RequestMapping("/jpa")
public class JpaJsonController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping("/rest.json")
    public String index(String param){
        return "rest controller: param "+param;
    }
    @RequestMapping("/save.json")
    public String setUser(String name,String password){
        User user=new User();
        user.setName(name);
        user.setPassword(password);

        userService.saveUser(user);
        return "Success  "+ user.toString();

    }
    @RequestMapping("/get.json")
    public String getUser(Long id){
        User user=userService.getUser(id);
        return user.toString();

    }

    @GetMapping("/getOrder.json")
    public String getOrder(Long id){
        Order o = orderService.getOrder(id);
        if(o==null){
            return "没有找到";
        }else{
            return o.toString();
        }

    }

    @GetMapping("/saveOrder.json")
    public String setOrder(BigDecimal money,BigDecimal rate){
        Order o = new Order();
        BigDecimal m3 = money.divide(rate,2,BigDecimal.ROUND_CEILING);
        o.setPrice(m3);
        orderService.saveOrder(o);
        return "success "+o.toString();
    }

    @GetMapping("/test.json")
    public String test(){


        return "ok";
    }
}
