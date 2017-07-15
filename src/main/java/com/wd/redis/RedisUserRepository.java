package com.wd.redis;

import com.wd.entity.RedisUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by woody on 2017/7/15.
 */
public interface RedisUserRepository extends CrudRepository<RedisUser,Integer> {
}
