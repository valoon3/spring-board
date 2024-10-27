package com.project.springboard.api.auth.util;

import com.project.springboard.error.ErrorType;
import com.project.springboard.error.exception.UnauthorizedException;
import org.springframework.util.StringUtils;

/**
 * 토큰 헤더를 검증하는 유틸 클래스
 * */
public class AuthorizationHeaderUtils {

    public static void validateAuthorization(String authorizationHeader) {

        // 1. authorizationHeader 필수 체크
        if(!StringUtils.hasText(authorizationHeader)) {
            throw new UnauthorizedException(ErrorType.NOT_EXISTS_AUTHORIZATION);
        }

        // 2. authorizationHeader Bearer 체크
        String[] authorizations = authorizationHeader.split(" ");
        if(authorizations.length < 2 || (!"Bearer".equals(authorizations[0]))) {
            throw new UnauthorizedException(ErrorType.NOT_VALID_BEARER_GRANT_TYPE);
        }
    }
}
