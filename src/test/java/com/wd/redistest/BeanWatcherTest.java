package com.wd.redistest;

import com.wd.Application;
import com.wd.springbeanwatcher.BeanWatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

/**
 * Created by wd on 2017/7/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class BeanWatcherTest {

    @Autowired
    BeanWatcher watcher;

    @Test
    public void test(){

        List<String> names = watcher.getBeanNamesOfType(RedisTest.class);
        System.out.println(names);
    }
}
