package com.org.trustservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerResponse {

    private ControllerResponse() {
    }

    public static <T> ResponseEntity<T> getOkResponseEntity(T response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> getServerErrorResponseEntity(T details) {
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity<T> getNotFoundResponseEntity(T details) {
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }
}
