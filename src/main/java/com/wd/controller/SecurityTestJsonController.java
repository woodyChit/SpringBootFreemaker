package com.wd.controller;

import com.wd.entity.security.SysResource;
import com.wd.entity.security.SysResourceRole;
import com.wd.entity.security.SysRole;
import com.wd.entity.security.SysUser;
import com.wd.model.JsonModel;
import com.wd.security.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by woody on 2017/8/7.
 */
@RestController
@RequestMapping("/sec")
public class SecurityTestJsonController {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysResourceService sysResourceService;
    @Autowired
    SysResourseRoleService sysResourseRoleService;

    @GetMapping("/user.json")
    public JsonModel getUser(Integer id){
        SysUser user = sysUserService.findOne(id);
        JsonModel model = new JsonModel();
        model.put("user",user);
        return model;
    }
    @GetMapping("/role.json")
    public JsonModel role(Integer id) {
        SysRole user = sysRoleService.findOne(id);
        JsonModel model = new JsonModel();
        model.put("role", user);
        return model;
    }
    @GetMapping("/re.json")
    public JsonModel re(Integer id) {
        SysResource user = sysResourceService.findOne(id);
        JsonModel model = new JsonModel();
        model.put("re", user);
        return model;
    }
    @GetMapping("/rer.json")
    public JsonModel rer(Integer id) {
        SysResourceRole user = sysResourseRoleService.findOne(id);
        JsonModel model = new JsonModel();
        model.put("rer", user);
        return model;
    }
}
