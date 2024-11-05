package com.project.springboard.common;

import com.project.springboard.common.dto.ResponseBodyDto;
import com.project.springboard.error.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResponse<T> extends ResponseEntity<ResponseBodyDto<T>> {
    public BaseResponse(T data, HttpStatus status) {
        super(new ResponseBodyDto<>(data), status);
    }

    public BaseResponse(ErrorType errorType) {
        super(new ResponseBodyDto<>(
                null,
                errorType.getErrorCode(),
                errorType.getErrorMessage()
                ),
                errorType.getHttpStatus()
        );
    }

    // 필요에 따라 상태 추가
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(data, HttpStatus.OK);
    }

    public static <T> BaseResponse<T> fail(ErrorType errorType) {
        return new BaseResponse<>(errorType);
    }
}
