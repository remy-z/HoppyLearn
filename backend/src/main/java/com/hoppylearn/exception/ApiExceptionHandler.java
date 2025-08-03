package com.hoppylearn.exception;

import org.springframework.http.ResponseEntity;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hoppylearn.model.response.ErrorResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.slf4j.Logger;

@ControllerAdvice()
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    // generic fallback handler for unexpected runtime exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleApiRequestException(Exception e) {
        FallbackException wrapped = new FallbackException(e.getMessage(), e);
        return handleException(wrapped);
    }

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        return handleException(e);
    }

    private ResponseEntity<Object> handleException(ApiRequestException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getError(),
                e.getHttpStatus());

        String logMessage = String.format("[{}] {} - {}", errorResponse.getTrackingId(), e.getClass().getSimpleName(),
                e);

        if (e instanceof FallbackException) {
            logger.error(logMessage);
        } else {
            logger.warn(logMessage);
        }

        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

}
