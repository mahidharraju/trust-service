package com.org.trustservice.excpetion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericAPIException extends RuntimeException {

    public GenericAPIException(String message) {
        super(message);
    }

    public GenericAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
