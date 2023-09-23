package com.example.fileupload.Component;

import com.example.fileupload.interceptor.SubscriberInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SubscriberInterceptorRegistry implements WebMvcConfigurer {
    @Autowired
    SubscriberInterceptor subscriberInterceptors;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(subscriberInterceptors);
    }
}
