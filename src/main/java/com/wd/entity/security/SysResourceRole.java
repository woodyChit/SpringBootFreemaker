package com.wd.entity.security;

import javax.persistence.*;

/**
 * Created by woody on 2017/8/7.
 */
@Entity
@Table(catalog = "test",name = "t_sys_resource_role")
public class SysResourceRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "resource_id")
    private Integer resourceId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysResourceRole{");
        sb.append("id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", resourceId=").append(resourceId);
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}
