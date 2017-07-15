package com.wd.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * Created by woody on 2017/7/15.
 * 结合RedisUserRepository  演示了@RedisHash 和 @Id 的使用。
 */
@RedisHash("redisUser")
public class RedisUser {

    @Id
    private Integer id;     //     CrudRepository<RedisUser,Integer>  中的Integer 对应
    private String name;
    private String password;

    public RedisUser(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "RedisUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
