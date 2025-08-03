package com.hoppylearn.exception;

import org.springframework.http.HttpStatus;

public class IllegalUserInputException extends ApiRequestException {
    public IllegalUserInputException(String message) {
        super(message);
    }

    public IllegalUserInputException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getError() {
        return "Invalid request parameters";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
