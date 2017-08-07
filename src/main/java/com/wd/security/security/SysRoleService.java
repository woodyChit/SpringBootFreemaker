package com.wd.security.security;

import com.wd.entity.security.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by woody on 2017/8/7.
 */
public interface SysRoleService extends JpaRepository<SysRole,Integer>{
}
