package com.wd.mybatisservice;

import com.wd.entity.Order;
import com.wd.mymapper.MapperProxyFactory;
import com.wd.mymapper.MyOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by woody on 2017/7/29.
 */
@Service
public class MyOrderService {

    @Autowired
    MapperProxyFactory proxyFactory;

    public Order get(int id){
        MyOrderMapper orderService = proxyFactory.create(MyOrderMapper.class);
        return orderService.get(id);
    }

    public void save(Order order){
        proxyFactory.create(MyOrderMapper.class).save(order);
    }
}
