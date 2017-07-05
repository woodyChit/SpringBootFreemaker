package com.wd.service;

import com.wd.dao.OrderDao;
import com.wd.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wd on 2017/7/5.
 */
@Service
@Transactional(transactionManager = "jpaTransactionManager")
public class OrderServiceImpl {

    @Autowired
    OrderDao orderDao;

    public Order saveOrder(Order order){
        orderDao.save(order);
        return order;
    }

    public Order getOrder(Long id){

        return orderDao.findOne(id);

    }
}
