package com.wd.security.security;

import com.wd.entity.security.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by woody on 2017/8/7.
 */
public interface SysUserService extends JpaRepository<SysUser,Integer>{

}
