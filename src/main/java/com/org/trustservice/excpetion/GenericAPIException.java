package com.org.trustservice.excpetion;

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

  public GenericAPIException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
