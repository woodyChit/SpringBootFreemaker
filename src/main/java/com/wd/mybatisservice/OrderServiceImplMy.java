package com.wd.mybatisservice;

import com.wd.entity.Order;
import com.wd.entity.User;
import com.wd.mapper.OrderMapper;
import com.wd.service.OrderServiceImpl;
import com.wd.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

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

    @Override
    public Order update(Order order) {
        orderDao.update(order);
        return order;
    }
}
