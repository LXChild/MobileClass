package com.chong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 捕获全局异常
 * Created by LXChild on 19/03/2017.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String DEFAULT_ERROR_VIEW = "/error";
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        LOGGER.error("系统异常", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURI());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
