package com.org.trustservice.util;

import com.org.trustservice.excpetion.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerResponse {

    private ControllerResponse() {
    }

    public static <T> ResponseEntity<T> getOkResponseEntity(T response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <ErrorDetails> ResponseEntity<ErrorDetails> getServerErrorResponseEntity(ErrorDetails details) {
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <ErrorDetails> ResponseEntity<ErrorDetails> getNotFoundResponseEntity(ErrorDetails details) {
        return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
    }
}
