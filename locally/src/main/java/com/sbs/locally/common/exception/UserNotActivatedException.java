package com.sbs.locally.common.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotActivatedException extends AuthenticationException {
	public UserNotActivatedException() {
		super("이메일 인증이 아직 완료되지 않았습니다. 확인 후 로그인해주세요.");
	}
	
	public UserNotActivatedException(String message) {
		super(message);
	}
}
