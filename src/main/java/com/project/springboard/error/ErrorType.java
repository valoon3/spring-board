package com.project.springboard.error;

import lombok.Getter;

@Getter
public enum ErrorType {

    TEST("001", "badrequest exception test"),

    NOT_EXISTS_AUTHORIZATION("A-003", "Authorization Header가 빈값입니다."),
    NOT_VALID_BEARER_GRANT_TYPE("A-004", "인증 타입이 Bearer 타입이 아닙니다."),

    ;

    // 에러 코드 (예: "A-001", "M-002" 등)
    private String errorCode;

    // 에러 메시지 (예: "토큰이 만료되었습니다.", "이미 가입된 회원입니다." 등)
    private String errorMessage;

    /**
     * ErrorType 생성자입니다.
     *
     * @param errorCode 에러 코드
     * @param errorMessage 에러 메시지
     */
    ErrorType(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
