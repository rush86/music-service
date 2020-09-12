package com.skelton.model.api;

import org.springframework.lang.NonNull;

public class ErrorDTO {

    @NonNull
    private final ErrorCode errorCode;
    @NonNull
    private final String message;
    private final String stackTrace;

    public ErrorDTO(ErrorCode errorCode, String stackTrace, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public String getMessage() {
        return message;
    }
}