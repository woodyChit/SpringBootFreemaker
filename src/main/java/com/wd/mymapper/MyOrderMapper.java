package com.wd.mymapper;

import com.wd.entity.Order;

/**
 * Created by wd on 2017/7/5.
 * 演示用的mapper 接口
 */
public interface MyOrderMapper {
    Order get(int id);
    void save(Order newOrder);
}
