package com.chong.utils;

import com.chong.entity.User;
import com.chong.service.UserService;
import com.chong.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户工具集
 * Created by LXChild on 23/04/2017.
 */
@Component
public class UserUtil {

    private final UserService userService;

    @Autowired
    public UserUtil(UserService userService) {
        this.userService = userService;
    }

    public  Long getCurrentUserId(HttpServletRequest request) {
        String creatorName = request.getRemoteUser();
        BaseValidator.notEmptyString(creatorName);
        User user = userService.findByName(creatorName);
        BaseValidator.notEmpty(user);
        BaseValidator.notEmpty(user.getId());
        return user.getId();
    }
}
