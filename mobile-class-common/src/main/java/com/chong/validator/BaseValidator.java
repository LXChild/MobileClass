package com.chong.validator;

import com.chong.exception.ParamException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验器工具集
 * Created by LXChild on 19/03/2017.
 */
public final class BaseValidator {

    private BaseValidator() {}

    public static void notEmptyString(String target) {
        if (target == null || target.length() == 0) {
            throw new ParamException.NullParamException();
        }
    }

    public static void notEmptyString(String target, String message) {
        if (target == null || target.length() == 0) {
            throw new ParamException.NullParamException(message);
        }
    }

    public static void stringNotMatch(String target, String reg, String message) {
        notEmptyString(target, "验证字符串不能为空");
        notEmptyString(reg, "匹配的正则表达式不能为空");
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(target);
        if (!m.matches()) {
            throw new ParamException.MalformedParamException(message);
        }
    }

    public static void stringNotMatch(String target, String reg) {
        notEmptyString(target, "验证字符串不能为空");
        notEmptyString(reg, "匹配的正则表达式不能为空");
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(target);
        if (!m.matches()) {
            throw new ParamException.MalformedParamException();
        }
    }

    public static void notEmpty(Object object) {
        if (object == null) {
            throw new ParamException.NullParamException();
        }
    }

    public static void notEmpty(Object object, String message) {
        if (object == null) {
            throw new ParamException.NullParamException(message);
        }
    }
}
