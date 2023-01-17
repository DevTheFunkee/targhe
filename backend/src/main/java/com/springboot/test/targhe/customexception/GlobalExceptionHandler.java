package com.springboot.test.targhe.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private class ErrorResponse {
		private HttpStatus status;
		private int intStatus;
		private String message;
		private String cause;

		public ErrorResponse(HttpStatus internalServerError, int value, String message, String cause) {
			this.status = internalServerError;
			this.intStatus = value;
			this.message = message;
			this.cause = cause;
		}

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(),
						ex.getMessage(), ex.getCause().getMessage()));
	}

}
