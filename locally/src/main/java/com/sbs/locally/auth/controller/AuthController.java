package com.sbs.locally.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.locally.auth.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
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
}
