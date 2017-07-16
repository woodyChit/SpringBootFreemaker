package com.wd.controller;

import com.wd.entity.Order;
import com.wd.mybatisservice.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by wd on 2017/7/5.
 */
@RestController
@RequestMapping("/mybatis")
public class MybatisJsonController {

    @Autowired
    OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(MybatisJsonController.class);

    @GetMapping("/getorder.json")
    public Order get(int id){
        Order o = orderService.get(id);
        return o;
    }

    @GetMapping("/setorder.json")
    public String save(BigDecimal money){
        Order o = new Order();
        o.setPrice(money);
        orderService.save(o);
        return "Save ok :"+ o.toString();
    }
}
