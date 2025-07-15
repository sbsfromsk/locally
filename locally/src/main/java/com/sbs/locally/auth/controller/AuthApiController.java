package com.sbs.locally.auth.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.locally.auth.forms.EmailRequestForm;
import com.sbs.locally.auth.service.AuthService;
import com.sbs.locally.email.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthApiController {

	private final AuthService authService;

	@PostMapping("/password")
	public ResponseEntity<?> findPassword(@Valid @RequestBody EmailRequestForm email, BindingResult bindingResult)
			throws MessagingException {

		long start = System.currentTimeMillis();
		System.out.println("히히ㅍ히");
		log.info("쿠쿠키쿠쿠키 :{}", email);

		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream().map(error -> error.getDefaultMessage())
					.peek(errorMsg -> log.info("{}", errorMsg)).toList();

			log.info("움치치움치치");
			return ResponseEntity.badRequest().body(errors);
		}

		authService.sendResetPasswordEmail(email.getEmail());

		long end = System.currentTimeMillis();
		
		log.info("전체 처리 시간: {} ms", (end- start));
		
		return ResponseEntity.noContent().build();
	}
}
