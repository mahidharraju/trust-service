package com.org.trustservice.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NoDataFoundException extends RuntimeException {

  public NoDataFoundException(final String message) {
    super(message);
  }

}
