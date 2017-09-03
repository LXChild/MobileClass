package com.chong.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Conditional(SwaggerCondition.class)
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket userAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Mobile Class").description("Mobile Class Restful Api Docs")
                .termsOfServiceUrl("https://github.com/LXChild")
                .contact(new Contact("Wang Chong", "https://github.com/LXChild",
                        "wangchong@finupgroup.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE").version("2.0")
                .build();
    }
}