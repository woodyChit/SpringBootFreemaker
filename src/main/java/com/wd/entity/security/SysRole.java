package com.wd.entity.security;

import javax.persistence.*;

/**
 * Created by woody on 2017/8/7.
 */
@Entity
@Table(catalog = "test",name = "t_sys_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;


    @Column(name = "user_id",nullable = false)
    private Integer sysUser;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysRole{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", sysUser=").append(sysUser);
        sb.append('}');
        return sb.toString();
    }

    public SysRole() {
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSysUser() {
        return sysUser;
    }

    public void setSysUser(Integer sysUser) {
        this.sysUser = sysUser;
    }

    public SysRole(String name, Integer sysUser) {
        this.name = name;
        this.sysUser = sysUser;
    }
}
