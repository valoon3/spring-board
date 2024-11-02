package com.project.springboard.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ResponseBodyDto<T> {
    private T data;
    private boolean success;
    private String message;
    private String logMessage;

    public ResponseBodyDto(T data) {
        this.data = data;
        this.success = true;
    }

    public ResponseBodyDto(T data, String message) {
        this(data);
        this.message = message;
    }

    public ResponseBodyDto(T data, String message, String logMessage) {
        this(data, message);
        this.logMessage = logMessage;
    }
}
