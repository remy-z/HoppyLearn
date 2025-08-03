package com.hoppylearn.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiRequestException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getError() {
        return "Resource not found";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
