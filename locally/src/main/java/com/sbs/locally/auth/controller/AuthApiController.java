package com.sbs.locally.auth.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.locally.auth.forms.EmailRequestForm;
import com.sbs.locally.auth.forms.ResetPasswordForm;
import com.sbs.locally.auth.service.AuthService;
import com.sbs.locally.common.enums.TokenType;
import com.sbs.locally.common.service.TokenService;
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
	private final TokenService tokenService;

	@PostMapping("/findPassword")
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

		authService.sendResetPasswordEmail(email.getEmail(), TokenType.RESET_PASSWORD);

		long end = System.currentTimeMillis();

		log.info("전체 처리 시간: {} ms", (end - start));

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordForm form, BindingResult bindingResult) {

		log.info("비밀번호 변경 중...");
		log.info("password: {}, {}", form.getPassword1(), form.getPassword2());

		// 1. 토큰 검증
		Boolean isValid = tokenService.isValidToken(form.getPasswordToken());
		log.info("토큰 {}, 유효 여부: {}", form.getPasswordToken(), isValid);

		// 2. 토큰 유효하지 않는다면...?
		// invalid-token으로 이동해야 함.
		if (!isValid) {
			log.info("유효 시간 지남!");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "invalid_token"));
		}

		// 3. 비밀번호 검증
		if (!form.getPassword1().equals(form.getPassword2())) {

			log.info("여기 진행중!");

			bindingResult.rejectValue("password1", "NotEqual", "비밀번호가 일치하지 않습니다.");

		}

		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream().map(error -> error.getDefaultMessage())
					.peek(errorMsg -> log.info("{}", errorMsg)).toList();

			return ResponseEntity.badRequest().body(errors);
		}

		// 4. 비밀번호 변경 시작
		authService.resetPassword(form.getPasswordToken(), form.getPassword1());

		return ResponseEntity.noContent().build();
	}
}
