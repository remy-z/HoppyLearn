package com.hoppylearn.exception;

import org.springframework.http.HttpStatus;

public class FallbackException extends ApiRequestException {
    public FallbackException(String message) {
        super(message);
    }

    public FallbackException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getError() {
        return "An unexpected error occurred";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
