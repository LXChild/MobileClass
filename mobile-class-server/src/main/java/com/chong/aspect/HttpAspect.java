package com.chong.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 记录请求日志切面
 * Created by LXChild on 04/04/2017.
 */
@Aspect
@Component
public class HttpAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.chong.controller.*.*(..))")
    public void log() {}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求 URL
        LOGGER.info("url = {}", request.getRequestURL());
        // 获取请求 method
        LOGGER.info("method = {}", request.getMethod());
        // 获取请求者 ip
        LOGGER.info("ip = {}", request.getRemoteAddr());
        // 获取请求类方法
        LOGGER.info("class method = {}", joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        // 获取请求类方法参数
        LOGGER.info("args = {}", Arrays.toString(joinPoint.getArgs()));
    }

    @After("log()")
    public void doAfter() {
        LOGGER.info("-----------------------------------------------");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        LOGGER.info("response = {}", object.toString());
    }
}
