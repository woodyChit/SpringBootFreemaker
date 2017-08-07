package com.wd.entity.security;

import javax.persistence.*;

/**
 * Created by woody on 2017/8/7.
 */
@Entity
@Table(catalog = "test",name = "t_sys_resource")
public class SysResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "url")
    private String url;
    @Column(name ="resource_id")
    private Integer resourseId;
    @Column(name = "method_name")
    private String methodName;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysResource{");
        sb.append("id=").append(id);
        sb.append(", url='").append(url).append('\'');
        sb.append(", resourseId='").append(resourseId).append('\'');
        sb.append(", methodName='").append(methodName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getResourseId() {
        return resourseId;
    }

    public void setResourseId(Integer resourseId) {
        this.resourseId = resourseId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
