package com.wd.mybatisservice;

import com.wd.entity.Order;

/**
 * Created by wd on 2017/7/5.
 */
public interface OrderService {
    Order get(int i);
    Order save(Order order);
}
