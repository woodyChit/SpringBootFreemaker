package com.wd.security;

import com.wd.entity.security.SysRole;
import com.wd.entity.security.SysUser;
import com.wd.security.security.SysRoleService;
import com.wd.security.security.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wd on 2017/8/7.
 * 存储用户名字、密码和角色
 */
@Service
@Transactional(readOnly = true)
public class CostomUserService implements UserDetailsService {
    @Autowired
    SysUserService userService;
    @Autowired
    SysRoleService sysRoleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.getOne(1);   //假如
        if(user==null)
            throw  new UsernameNotFoundException("username "+username);
        SysRole role = sysRoleService.findOne(1);
        SysRole role2 = sysRoleService.findOne(2);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
        authorities.add(authority);     //创建一个假用户
        SimpleGrantedAuthority authority2 = new SimpleGrantedAuthority(role2.getName());
        authorities.add(authority2);     //创建一个假用户
        return new User(user.getName(),user.getPassword(),authorities);
    }
}
