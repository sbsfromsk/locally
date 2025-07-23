package com.sbs.locally.common.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<?> handleInvalidToken(InvalidTokenException e) {
		
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(Map.of("error", "invalid_token", "message", e.getMessage()));
	}
}
