package com.chong.controller;

import com.chong.properties.RoleProperties;
import com.chong.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户角色视图控制器
 * Created by LXChild on 26/04/2017.
 */
@Controller
@RequestMapping("/userRoles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "')")
    public void update(Long userId, Integer roleId) {
        userRoleService.update(userId, roleId);
    }
}
