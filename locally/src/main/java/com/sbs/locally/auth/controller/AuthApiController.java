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
	public ResponseEntity<?> findPassword(@Valid @RequestBody EmailRequestForm email, BindingResult bindingResult) {
		
		System.out.println("히히ㅍ히");
		log.info("쿠쿠키쿠쿠키 :{}", email);
		
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors()
					.stream()
					.map(error -> error.getField() + "흠냐링 ㅠㅠ: " + error.getDefaultMessage())
					.toList();
			
			log.info("움치치움치치");
			return ResponseEntity.badRequest().body(errors);
		}
		
		return ResponseEntity.noContent().build();
	}
}
