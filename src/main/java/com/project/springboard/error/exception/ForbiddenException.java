package com.project.springboard.error.exception;

import com.project.springboard.error.ErrorType;
import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    public ForbiddenException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ForbiddenException(ErrorType errorType) {
        this(errorType.getErrorCode(), errorType.getErrorMessage());
    }

    public static ForbiddenException of(String errorCode, String errorMessage) {
        return new ForbiddenException(errorCode, errorMessage);
    }

    public static ForbiddenException of(ErrorType errorType) {
        return new ForbiddenException(errorType);
    }

}
