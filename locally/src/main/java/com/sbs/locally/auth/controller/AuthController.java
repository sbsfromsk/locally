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
import com.sbs.locally.member.entity.Member;
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
	private final MemberService memberService;

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

	/**
	 * 회원가입 시, 토큰 유효한지 확인 후, Member 객체의 enabled = true로 바꿈.
	 */
	@GetMapping("/signUp")
	public String signUp(@RequestParam("token") String token, Model model, ResetPasswordForm resetPasswordForm) {

		// 1. 토큰 유효성 검사 후 멤버 가져오기...
		log.info("토큰: {}", token);

		VerificationToken verificationToken = tokenService.getTokenAndMember(token);
		Member member = verificationToken.getMember();
		
		// 2. member enabled = true
		memberService.activateMember(member);

		
		// 3. 토큰 만료하기
		tokenService.invalidToken(verificationToken);
		
		return "redirect:/";
	}

	@GetMapping("/invalidToken")
	public String invalidToken() {

		return "/auth/invalid-token";
	}

}
