package com.example.jpa_test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 컨트롤러에 대한 모든 경로를 허용
        registry.addMapping("/**")
                // 프론트에 대한 모든 경로를 허용
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                // 쿠키전달을 허용
                .allowCredentials(true);
    }
}