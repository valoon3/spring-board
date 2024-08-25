package com.project.springboard.config;

import com.project.springboard.filter.RequestAndResponseLoggingFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean<SampleHttpFilter> loggingFilter(){
//        FilterRegistrationBean<SampleHttpFilter> registrationBean = new FilterRegistrationBean<>();
//
//        registrationBean.setOrder(1);
//        registrationBean.setFilter(new SampleHttpFilter());
//        registrationBean.addUrlPatterns("*"); // 필터를 적용할 URL 패턴
//
//        return registrationBean;
//    }

//    @Bean
//    public FilterRegistrationBean<RequestAndResponseLoggingFilter> testFilter() {
//        FilterRegistrationBean<RequestAndResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();
//
//        registrationBean.setOrder(1);
//        registrationBean.setFilter(new RequestAndResponseLoggingFilter());
//        registrationBean.addUrlPatterns("*"); // 필터를 적용할 URL 패턴
//
//        return registrationBean;
//    }

    @Bean
    public FilterRegistrationBean<Filter> registerRequestResponseLoggingFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(requestAndResponseLoggingFilter());
        registration.setUrlPatterns(Arrays.asList("/*"));
        registration.setName("requestResponseLoggingFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public Filter requestAndResponseLoggingFilter() {
        return new RequestAndResponseLoggingFilter();
    }
}
