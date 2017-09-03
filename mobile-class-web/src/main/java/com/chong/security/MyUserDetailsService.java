package com.chong.security;

import com.chong.entity.Role;
import com.chong.entity.User;
import com.chong.entity.UserRole;
import com.chong.service.RoleService;
import com.chong.service.UserRoleService;
import com.chong.service.UserService;
import com.chong.validator.BaseValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 提供了获取 UserDetails 的方式,最终生成用户和权限共同组成的 UserDetails
 * Created by LXChild on 03/04/2017.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private final RoleService roleService;

    private final UserRoleService userRoleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    public MyUserDetailsService(RoleService roleService, UserService userService, UserRoleService userRoleService) {
        this.roleService = roleService;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        BaseValidator.notEmpty(username, "用户名不能为空");
        LOGGER.info("loadUserByUsername --> [{}]", username);

        User user = userService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found.");
        }
        UserRole userRole = userRoleService.findByUserId(user.getId());
        Role userRoles = roleService.findOne(userRole.getRoleId());
        return new MyUserDetails(user, userRoles);
    }
}
