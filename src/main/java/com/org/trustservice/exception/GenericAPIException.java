package com.org.trustservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class GenericAPIException extends RuntimeException {

  public GenericAPIException(final String message) {
    super(message);
  }

}
