package com.sbs.locally.common.exception;

public class InvalidTokenException extends RuntimeException {
	public InvalidTokenException() {
		super("토큰이 유효하지 않습니다.");
	}
	
	public InvalidTokenException(String message) {
		super(message);
	}
}
