package com.sbs.locally.common.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(annotations = Controller.class)
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidTokenException.class)
	public String handleInvalidToken(InvalidTokenException ex) {
		log.info("유효하지 않은 토큰 발생");
		return "/auth/invalid-token";
	}
}
