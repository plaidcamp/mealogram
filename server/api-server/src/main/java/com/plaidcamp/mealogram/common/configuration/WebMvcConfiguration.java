package com.plaidcamp.mealogram.common.configuration;

import com.plaidcamp.mealogram.common.interceptor.AuthCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfiguration implements WebMvcConfigurer {

//    @Autowired
//    private final AuthCheckInterceptor authCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor()
    }
}
