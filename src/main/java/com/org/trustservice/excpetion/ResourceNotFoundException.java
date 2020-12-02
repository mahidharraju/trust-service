package com.org.trustservice.excpetion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(final String message) {
    super(message);
  }

  public ResourceNotFoundException(
      final String message,
      final Throwable cause) {
    super(message, cause);
  }
}
