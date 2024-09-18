package com.project.springboard.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private List<String> notAuthorizationUri = List.of(
            "/api/auth/signup"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. Authorization Header 검증
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 2. 인증 생략
        String requestURI = httpServletRequest.getRequestURI();
        if (notAuthorizationUri.stream().anyMatch(uri -> uri.equals(requestURI))) {
            chain.doFilter(request, response);
        }
    }
}
