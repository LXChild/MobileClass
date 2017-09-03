package com.chong.utils;

import com.chong.dataobject.Result;
import com.chong.enums.ResultCode;

/**
 * 返回结果工具
 * Created by LXChild on 04/04/2017.
 */
public class ResultUtils {

    private ResultUtils() {}

    public static Result success(String url, Object object) {
        Result result = new Result();
        result.setCode(ResultCode.OK);
        result.setUrl(url);
        result.setData(object);
        return result;
    }

    public static Result error(ResultCode code, String url) {
        Result result = new Result();
        result.setCode(code);
        result.setUrl(url);
        return result;
    }

    public static Result error(ResultCode code, String url,  String message) {
        Result result = new Result();
        result.setCode(code);
        result.setUrl(url);
        result.setMsg(message);
        return result;
    }
}
