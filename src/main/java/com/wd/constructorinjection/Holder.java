package com.wd.constructorinjection;

import com.wd.entity.User;
import com.wd.init.MySetting;
import org.springframework.stereotype.Component;

/**
 * Created by wd on 2017/6/30.
 * 演示了 自动注册扫描的bean ,默认注入。
 */
@Component
public class Holder {

    private MySetting setting;
    public Holder(MySetting setting){
        this.setting = setting;
    }

    @Override
    public String toString() {
        return "Holder{" +
                "setting=" + setting +
                '}';
    }
}
