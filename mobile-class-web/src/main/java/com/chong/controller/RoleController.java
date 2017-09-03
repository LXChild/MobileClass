package com.chong.controller;

import com.chong.entity.Role;
import com.chong.properties.RoleProperties;
import com.chong.service.RoleService;
import com.chong.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 角色控制器
 * Created by LXChild on 26/04/2017.
 */
@Controller
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String toUserPage() {
        return "/role/list";
    }

    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "')")
    public TableVO<Role> findAllByComment(String comment) {
        List<Role> roles;
        if (comment != null && !"".equals(comment)) {
            roles = roleService.findAllByComment(comment);
        } else {
            roles = roleService.findAll();
        }
        return new TableVO<>((long) roles.size(), roles);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "')")
    public String save(String tag, String comment) {
        roleService.save(new Role(tag, comment, new Date()));
        return "redirect:/role";
    }

    @PostMapping("update")
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "')")
    public String update(Integer id, String tag, String comment) {
        roleService.update(id, tag, comment);
        return "redirect:/role";
    }
}
