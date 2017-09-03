package com.chong.validator;

import com.chong.entity.User;

/**
 * 用户验证器
 * Created by LXChild on 05/04/2017.
 */
public final class UserValidator {

    private UserValidator() {}

    public static void notEmptyNameAndPassword(User user) {
        BaseValidator.notEmptyString(user.getName(), "用户名不能为空");
        BaseValidator.notEmptyString(user.getPassword(), "密码不能为空");
    }

    public static void notEmpty(User user) {
        BaseValidator.notEmpty(user);
        BaseValidator.notEmptyString(user.getName(), "用户名不能为空");
        BaseValidator.notEmptyString(user.getEmail(), "邮箱不能为空");
        BaseValidator.notEmptyString(user.getMobile(), "手机号不能为空");
        BaseValidator.notEmpty(user.getEnable(), "用户状态不能为空");
        BaseValidator.notEmptyString(user.getRealName(), "真实姓名不能为空");
        BaseValidator.notEmpty(user.getUpdateTime(), "更新时间不能为空");

        String mobileReg = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        BaseValidator.stringNotMatch(user.getMobile(), mobileReg, "无效的手机号");

        String emailReg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        BaseValidator.stringNotMatch(user.getEmail(), emailReg, "无效的Email地址");
    }
}
