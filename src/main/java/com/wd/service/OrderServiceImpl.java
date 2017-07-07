package com.wd.service;

import com.wd.dao.OrderDao;
import com.wd.entity.Order;
import com.wd.mybatisservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by wd on 2017/7/5.
 */
@Service
@Transactional
public class OrderServiceImpl {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderService orderService;

    public Order saveOrder(Order order){
        orderDao.save(order);
        return order;

    }

    public Order getOrder(Long id){
        return orderDao.findOne(id);

    }
}
