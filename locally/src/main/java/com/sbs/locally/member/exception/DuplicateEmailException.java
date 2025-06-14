package com.sbs.locally.member.exception;

public class DuplicateEmailException extends RuntimeException {

	public DuplicateEmailException(String message) {
		super(message); // 부모 예외 클래스에 메시지 전달
	}
}
