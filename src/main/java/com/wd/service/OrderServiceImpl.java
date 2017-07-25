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
import java.math.MathContext;
import java.util.concurrent.TimeUnit;

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


    public void testTransaction(){
        Order o = getOrder(70L);
        o.setPrice(new BigDecimal(88));
        o.setPrice(o.getPrice().add(new BigDecimal(1)));
        System.out.println("thread 1 incre order = "+ o );
        orderDao.save(o);
        System.out.println("after save order = "+ o );
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after Sleep .");
        o.setPrice(o.getPrice().add(new BigDecimal(1)));
        System.out.println("after  save order = "+ o );
    }

    public void testTransactionSec(){

        Order o2 = getOrder(63L);
      //  orderDao.delete(63L);
        o2.setPrice(o2.getPrice().add(new BigDecimal(1)));
        System.out.println("  delete otherget = "+o2);
        //orderDao.save(o2);



    }
    public void testMybatisTransactionSec(){

        Order o2 = orderService.get(63);
        o2.setPrice(o2.getPrice().add(new BigDecimal(1)));
        System.out.println("  otherget = "+o2);
        orderService.save(o2);

    }
    public void testOtherThreadTransaction(Long id){
        Order o = getOrder(id);
        if(o!=null){
            o.setPrice(new BigDecimal(999));
        }else{
            System.out.println("do not get order");
        }

    }
}
