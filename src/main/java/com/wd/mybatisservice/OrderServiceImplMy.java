package com.wd.mybatisservice;

import com.wd.entity.Order;
import com.wd.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wd on 2017/7/5.
 */
@Service
@Transactional
public class OrderServiceImplMy implements OrderService{

    @Autowired
    OrderMapper orderDao;
    @Override
    public Order get(int i){
        return orderDao.get(i);
    }
    @Override
    public Order save(Order order){
        orderDao.save(order);
        return order;
    }
}
