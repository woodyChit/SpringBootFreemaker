package com.wd.dao;

import com.wd.entity.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wd on 2017/7/5.
 */
public interface OrderDao extends JpaRepository<Order,Long> {
}
