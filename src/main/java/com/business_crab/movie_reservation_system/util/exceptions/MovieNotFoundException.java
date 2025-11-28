package com.business_crab.movie_reservation_system.util.exceptions;

import org.springframework.http.HttpStatus;

public class MovieNotFoundException extends CustomException {
    public MovieNotFoundException(final String message, HttpStatus httpStatus) {
        super(message , httpStatus);
    }
}