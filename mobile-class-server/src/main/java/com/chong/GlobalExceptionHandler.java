package com.chong;

import com.chong.dataobject.Result;
import com.chong.enums.ResultCode;
import com.chong.exception.ParamException;
import com.chong.exception.PersistenceException;
import com.chong.exception.StorageException;
import com.chong.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 捕获全局异常
 * Created by LXChild on 19/03/2017.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result jsonErrorHandler(HttpServletRequest request, Exception e) {

        if (e instanceof ParamException) {
            return ResultUtils.error(((ParamException) e).getCode(), String.valueOf(request.getRequestURL()), e.getMessage());
        } else if (e instanceof PersistenceException) {
            return ResultUtils.error(((PersistenceException) e).getCode(), String.valueOf(request.getRequestURL()), e.getMessage());
        } else if (e instanceof StorageException) {
            return ResultUtils.error(((StorageException) e).getCode(), String.valueOf(request.getRequestURL()), e.getMessage());
        } else {
            LOGGER.error("【系统异常】", e);
            return ResultUtils.error(ResultCode.UNKNOWN, request.getRequestURI());
        }
    }

}
