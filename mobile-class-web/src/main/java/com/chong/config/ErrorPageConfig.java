package com.chong.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 配置异常页面
 * Created by LXChild on 08/04/2017.
 */
@Configuration
public class ErrorPageConfig {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403.html"));
        };
    }
}
