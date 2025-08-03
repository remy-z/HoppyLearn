package com.hoppylearn.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiRequestException extends RuntimeException {
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract String getError();

    public abstract HttpStatus getHttpStatus();
}
