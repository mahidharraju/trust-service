package com.org.trustservice.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class TrustServiceExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
      final ResourceNotFoundException exception,
      final WebRequest request) {
    return new ResponseEntity<>(
        buildErrorDetails(request, exception),
        HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(GenericAPIException.class)
  public ResponseEntity<ErrorDetails> handleGenericAPIException(
      final Exception exception,
      final WebRequest request) {
    return new ResponseEntity<>(
        buildErrorDetails(request, exception),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleException(
      final Exception exception,
      final WebRequest request) {
    return new ResponseEntity<>(
        buildErrorDetails(request, exception),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }


  private ErrorDetails buildErrorDetails(
      final WebRequest request,
      final Exception exception) {
    return ErrorDetails
        .builder()
        .timeStamp(LocalDateTime.now())
        .message(exception.getMessage())
        .info(request.getDescription(false))
        .build();
  }
}
