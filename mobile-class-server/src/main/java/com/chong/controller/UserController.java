package com.chong.controller;

import com.chong.dataobject.Result;
import com.chong.entity.User;
import com.chong.enums.ResultCode;
import com.chong.exception.PersistenceException;
import com.chong.service.UserService;
import com.chong.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户管理接口
 * Created by LXChild on 19/03/2017.
 */
@RestController
@RequestMapping("/api/v1/users")
@Api(value = "用户管理接口", protocols = "JSON")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiParam(required = true, name = "user", value = "用户")
    @ApiOperation(value = "添加用户", httpMethod = "POST", response = Result.class, notes = "添加用户")
    public Result add(HttpServletRequest request, @RequestBody @Valid User user, BindingResult bindingResult)
            throws PersistenceException.RepetitiveParamException {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCode.FAILED, String.valueOf(request.getRequestURL()),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(request.getRequestURI(), userService.add(user));
    }
}
