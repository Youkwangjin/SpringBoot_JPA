package com.minipro.springweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
            1. View 에서 접근할 경로로 접근하게 되면 스프링에서 실제 파일 저장 경로에서 찾아준다.
         */
        // View 에서 접근할 경로
        String resourcePath = "/upload/**";
        // 실제 파일 저장 경로
        String savePath = "file:///C:/work/SpringBoard/src/main/resources/static/spring_img/";
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}
