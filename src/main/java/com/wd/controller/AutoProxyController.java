package com.wd.controller;

import com.wd.entity.Order;
import com.wd.mybatisservice.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by woody on 2017/7/29.
 */
@RestController
@RequestMapping("/proxy")
public class AutoProxyController {
    @Autowired
    private MyOrderService orderService;

    @GetMapping("/order.json")
    public String get(int id){
        return orderService.get(id).toString();
    }

    @GetMapping("/saveOrder.json")
    public String save(BigDecimal p){
        Order o = new Order();
        o.setPrice(p);
        orderService.save(o);
        return o.toString();
    }
}
