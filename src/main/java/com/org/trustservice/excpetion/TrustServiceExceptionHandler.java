package com.org.trustservice.excpetion;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.org.trustservice.util.ControllerResponse;

@ControllerAdvice
public class TrustServiceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
		return ControllerResponse.getNotFoundResponseEntity(ErrorDetails
				.builder()
				.timeStamp(LocalDateTime.now())
				.message(exception.getMessage())
				.info(request.getDescription(false))
				.build());
	}


	@ExceptionHandler(GenericAPIException.class)
	public ResponseEntity<ErrorDetails> handleGenericAPIException(Exception exception, WebRequest request) {
		return ControllerResponse.getServerErrorResponseEntity(ErrorDetails
				.builder()
				.timeStamp(LocalDateTime.now())
				.message(exception.getMessage())
				.info(request.getDescription(false))
				.build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleException(Exception exception, WebRequest request) {
		ErrorDetails details = ErrorDetails
				.builder()
				.timeStamp(LocalDateTime.now())
				.message(exception.getMessage())
				.info(request.getDescription(false))
				.build();
		return ControllerResponse.getServerErrorResponseEntity(details);
	}
}
