package com.wd.redistest;

import com.wd.Application;
import com.wd.entity.User;
import com.wd.redis.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.*;
/**
 * Created by wd on 2017/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void testSetAndGet(){
        User u = new User();
        u.setName("chitoege");
        u.setPassword("qwertt");
        u.setId(32L);
        redisService.setValue("userTest",u);
        User get = redisService.getValue("userTest",User.class);
        assertTrue(get.getName().equals(u.getName()));
        assertTrue(get.getPassword().equals(u.getPassword()));
        assertTrue(get.getId().equals(u.getId()));
        assertFalse(get==u);
    }

    @Test
    public void testRank(){
        redisService.addNewScore("woody",100);
        redisService.addNewScore("chitoge",98.2);
        redisService.addNewScore("kky",89.0);
        redisService.addNewScore("uzi",28);
        Map<String,Double> rank = redisService.getRank(0,-1);
        System.out.println(rank);
        redisService.increScore("kky",9.8);
        rank = redisService.getRank(0,-1);
        System.out.println(rank);
        Long userrank = redisService.getUserRank("kky");
        Long nullUserRank = redisService.getUserRank("fwe");
        Long woodyRank = redisService.getUserRank("woody");
        Long cRank = redisService.getUserRank("uzi");
        assertTrue(userrank==2L);
        assertTrue(nullUserRank==null);
        assertTrue(woodyRank==3L);
        assertTrue(cRank==0L);
    }
}
