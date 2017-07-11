package com.wd.service;

import com.wd.dao.UserDao;
import com.wd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by root on 17-3-11.
 */

@Service
@Transactional
@CacheConfig(cacheNames = "jpaCache")
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderServiceImpl orderService;

    public void saveUser(User user){
        userDao.save(user);
    }
    @Cacheable
    public User getUser(Long id){
        return userDao.findOne(id);
    }
}
