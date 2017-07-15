package com.wd.redis;

import com.wd.entity.RedisUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by woody on 2017/7/15.
 *  支持类似于JPA 函数的查询函数，不用写实现，。
 */
public interface RedisUserRepository extends CrudRepository<RedisUser,Integer> {
}
