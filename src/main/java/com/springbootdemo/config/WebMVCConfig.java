package com.springbootdemo.config;

import com.springbootdemo.inteceptors.LoginProtectedInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {


    @Autowired
    private LoginProtectedInteceptor loginProtectedInteceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginProtectedInteceptor).addPathPatterns("/headline/**");

    }
}
