package com.lb.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截/admin/下的所有路径
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                 .excludePathPatterns("/admin")
                 .excludePathPatterns("/admin/login");
    }
}
