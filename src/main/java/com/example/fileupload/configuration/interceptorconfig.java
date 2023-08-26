package com.example.fileupload.configuration;

import com.example.fileupload.interceptor.generalinterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class interceptorconfig implements WebMvcConfigurer{
    @Bean
    public generalinterceptor generalinterceptor() {
        return new generalinterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(generalinterceptor()).addPathPatterns("/fileupload");
    }


}
