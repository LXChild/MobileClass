package com.chong.controller;

import com.chong.entity.Role;
import com.chong.entity.User;
import com.chong.entity.UserRole;
import com.chong.enums.RoleEnum;
import com.chong.properties.RoleProperties;
import com.chong.service.RoleService;
import com.chong.service.UserRoleService;
import com.chong.service.UserService;
import com.chong.utils.UserUtil;
import com.chong.vo.TableVO;
import com.chong.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户控制器
 * Created by LXChild on 19/03/2017.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    private final UserRoleService userRoleService;

    private final UserUtil userUtil;

    @Autowired
    public UserController(UserService userService, UserRoleService userRoleService, RoleService roleService, UserUtil userUtil) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        this.userUtil = userUtil;
    }

    @GetMapping
    public String toUserPage() {
        return "/user/list";
    }

    @GetMapping("/list")
    @ResponseBody
    @Transactional
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "')")
    public TableVO<UserVO> getUserList(int limit, int offset, String realName) {
        Page<User> users;
        Integer page = offset / limit;
        if (realName == null || "".equals(realName)) {
            users = userService.findAll(limit, page);
        } else {
            users = userService.findAllByRealName(limit, page, realName);
        }

        List<UserVO> userVOs = new ArrayList<>();

        for (User user : users.getContent()) {
             UserRole userRole = userRoleService.findByUserId(user.getId());
             Role role = roleService.findOne(userRole.getRoleId());
             userVOs.add(new UserVO(user, role));
        }

        return new TableVO<>(users.getTotalElements(), userVOs);
    }

    @PostMapping("/update")
    @Transactional
    @PreAuthorize("hasAnyRole('" + RoleProperties.SYS_ADMIN + "','" + RoleProperties.ADMIN + "')")
    public String update(Long id, String name, String realName, String mobile, String email,
                         Boolean enable, Integer roleId) {
        userService.update(id, name, realName, mobile, email, enable);
        userRoleService.update(id, roleId);
        return "redirect:/users";
    }

    @PostMapping("/register")
    @Transactional
    @ResponseBody
    public Boolean register (String name, String password, String mobile, String email, String realName) {
        User user = userService.add(new User(name, password, mobile, email, realName, new Date()));
        userRoleService.save(new UserRole(user.getId(), RoleEnum.STUDENT.getIndex(), new Date()));
        return true;
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request, ModelMap map) {
        Long userId = userUtil.getCurrentUserId(request);
        User user = userService.findOne(userId);

        map.addAttribute("user", user);
        return "/mine/profile";
    }
}
