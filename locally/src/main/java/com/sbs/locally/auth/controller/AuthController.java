package com.sbs.locally.auth.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.locally.auth.forms.ResetPasswordForm;
import com.sbs.locally.auth.service.AuthService;
import com.sbs.locally.common.entity.VerificationToken;
import com.sbs.locally.common.service.TokenService;
import com.sbs.locally.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	private final TokenService tokenService;

	/* ↓↓↓ 로그인 관련 ↓↓↓ */
	@GetMapping("/login")
	public String login() {

		return "/auth/login";
	}
	/* ↑↑↑ 로그인 관련 ↑↑↑ */

	/* ↓↓↓ 비밀번호 찾기 관련 ↓↓↓ */
	@GetMapping("/findPassword")
	public String findPassword() {
		log.info("ㅇㅇ");
		return "/auth/findPassword";
	}
	/* ↑↑↑ 비밀번호 찾기 관련 ↑↑↑ */

	@GetMapping("/resetPassword")
	public String resetPassword(@RequestParam("token") String token, Model model, ResetPasswordForm resetPasswordForm) {

		log.info("토큰: {}", token);

		Boolean validToken = tokenService.isValidToken(token);

		if (!validToken) {

			return "/auth/invalid-token";
		}
		// html에서 폼 만들기
		// formDTO 만들기
		return "/auth/resetPassword";
	}
	
	@GetMapping("/signUp")
	public String signUp(@RequestParam("token") String token, Model model, ResetPasswordForm resetPasswordForm) {

		log.info("토큰: {}", token);

		Boolean validToken = tokenService.isValidToken(token);

		if (!validToken) {

			return "/auth/invalid-token";
		}
		
		/*
		 * 1. member 객체의 enabled=true
		 * 2. Token 유효 끝내기
		 * 
		 * */
		authService.activateMember(token);
		
		// html에서 폼 만들기
		// formDTO 만들기
		return "/auth/sign-up";
	}
	
	@GetMapping("/invalidToken")
	public String invalidToken() {
		
		return "/auth/invalid-token";
	}

}
