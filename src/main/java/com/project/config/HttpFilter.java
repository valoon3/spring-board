package com.project.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        log.info("Filter init!");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        log.info("요청 Url : {}", req.getRequestURL());

        log.info("Filter 시작합니다!");
        chain.doFilter(request, response);
        log.info("Filter 끝납니다!");
    }

    @Override
    public void destroy() {
        log.info("Filter destroy");
        Filter.super.destroy();
    }
}
