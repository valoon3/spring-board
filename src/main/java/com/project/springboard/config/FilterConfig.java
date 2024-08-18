package com.project.springboard.config;

import com.project.springboard.filter.HttpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<HttpFilter> loggingFilter(){
        FilterRegistrationBean<HttpFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new HttpFilter());
        registrationBean.addUrlPatterns("*"); // 필터를 적용할 URL 패턴

        return registrationBean;
    }
}
