package com.musicservice.api;

import static com.musicservice.api.dto.ErrorCode.INVALID_ARGUMENT;
import static com.musicservice.api.dto.ErrorCode.UNAUTHORIZED_ACTIVITY;
import static com.musicservice.api.dto.ErrorCode.UNEXPECTED_ERROR;

import com.musicservice.api.dto.ErrorCode;
import com.musicservice.api.dto.ErrorDTO;
import com.musicservice.exception.UnauthorizedException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDTO handleIllegalArgument(IllegalArgumentException e, WebRequest webRequest) {
        logger.warn("IllegalArgumentException while handling request: {}", webRequest, e);
        return toErrorDTO(INVALID_ARGUMENT, e);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorDTO handleIllegalArgument(UnauthorizedException e, WebRequest webRequest) {
        logger.warn("UnauthorizedException while handling request: {}", webRequest, e);
        return toErrorDTO(UNAUTHORIZED_ACTIVITY, e);
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorDTO handleUnexpectedError(RuntimeException e, WebRequest webRequest) {
        logger.error("unexpected error while handling request: {}", webRequest, e);
        return toErrorDTO(UNEXPECTED_ERROR, e);
    }

    private ErrorDTO toErrorDTO(ErrorCode errorCode, Exception exception) {
        return new ErrorDTO(errorCode, getStackTraceAsString(exception), exception.getMessage());
    }

    private String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}