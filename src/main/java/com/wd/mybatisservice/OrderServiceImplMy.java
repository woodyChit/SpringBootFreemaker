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

    @Autowired
    UserServiceImpl jpaUserService;
    @Autowired
    OrderServiceImpl jpaOrderService;

    @Autowired
    PlatformTransactionManager transactionManager;
    @Override
    public Order get(int i){
        return orderDao.get(i);
    }
    @Override
    public Order save(Order order){
        User u = new User();
        u.setName("wwwtest_for_rollback");
        u.setPassword("123456");
        jpaUserService.saveUser(u);

        Order newOrder = new Order();
        newOrder.setPrice(new BigDecimal(100.2));
        jpaOrderService.saveOrder(newOrder);


        orderDao.save(order);
        if(order.getId()!=null){
           //throw new IllegalStateException("2");
        }
        return order;
    }
}
