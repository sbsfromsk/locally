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
	private final EmailService emailService;

	@PostMapping("/password")
	public ResponseEntity<?> findPassword(@Valid @RequestBody EmailRequestForm email, BindingResult bindingResult)
			throws MessagingException {

		System.out.println("히히ㅍ히");
		log.info("쿠쿠키쿠쿠키 :{}", email);

		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream().map(error -> error.getDefaultMessage())
					.peek(errorMsg -> log.info("{}", errorMsg)).toList();

			log.info("움치치움치치");
			return ResponseEntity.badRequest().body(errors);
		}

		// 메일 전송은 OK!
		// 계정을 찾고 계정이 없으면 메일을 보내지 않음
		// 계정이 있을 경우 토큰 생성

		// 1. 계정 찾기
		String toEmail = authService.findEmail(email.getEmail());

		if (!toEmail.isBlank()) {
			log.info("이메일 보내는 중... {}", toEmail);

			// 2. 계정이 존재할 경우 토큰 생성

			emailService.sendEmail(email.getEmail());
		} else {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.noContent().build();
	}
}
