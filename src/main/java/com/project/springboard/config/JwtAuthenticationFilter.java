package com.project.springboard.config;

import com.project.springboard.api.auth.TokenManager;
import com.project.springboard.api.auth.util.AuthorizationHeaderUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;

    private List<String> notAuthorizationUri = List.of(
            "/api/member/signup",
            "/api/member/login"
    );

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // 2. 등록된 uri 인증 생략
        if (notAuthorizationUri.contains(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        // 3. header Beaber 검증
        String authorizationHeader = request.getHeader("Authorization");

        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);

        // 4. 토큰 검증
        String token = authorizationHeader.split(" ")[1];
        tokenManager.validateToken(token);

        // 4. 스프링 시큐리티에 인증정보 세팅
        Authentication authentication = tokenManager.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }
}
