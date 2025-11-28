package com.business_crab.movie_reservation_system.util.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public CustomException(final String message , final HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}