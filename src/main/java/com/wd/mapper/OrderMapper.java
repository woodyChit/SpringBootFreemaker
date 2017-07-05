package com.wd.mapper;

import com.wd.entity.Order;

/**
 * Created by wd on 2017/7/5.
 */
public interface OrderMapper {
    Order get(int id);
    void save(Order newOrder);
}
