package com.hoppylearn.model.response;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
    private final String trackingId;
    private final String error;

    public ErrorResponse(String message, String error, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.error = error;
        this.timestamp = ZonedDateTime.now(ZoneId.of("Z"));
        this.trackingId = UUID.randomUUID().toString();
    }
}
