package com.sbs.locally.member.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.locally.member.exception.DuplicateEmailException;
import com.sbs.locally.member.exception.DuplicateNickNameException;
import com.sbs.locally.member.forms.SignupForm;
import com.sbs.locally.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/signup")
	public String signup(SignupForm signupForm) {

		return "/member/signup";
	}

	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute SignupForm form, BindingResult bindingResult, Model model) {

		
			// 1. 기본적인 유효성 검증
			if (bindingResult.hasErrors()) {
				// model.addAttribute("errors", bindingResult.getAllErrors());
				return "/member/signup";
			}

			/* 2. 복잡한 유효성 검증 START */

			// 2-1 비밀번호 일치, 불일치
			if (!form.getPassword1().equals(form.getPassword2())) {

				bindingResult.rejectValue("password1", "password.mismatch", "비밀번호가 일치하지 않습니다.");
				bindingResult.rejectValue("password2", "password.mismatch", "비밀번호가 일치하지 않습니다.");
			}

			// 2-2 아이디 중복 확인
			if (memberService.existsByEmail(form.getEmail())) {

				bindingResult.rejectValue("email", "email.duplicate", "이미 가입한 이메일입니다.");
			}

			// 2-3 닉네임 중복 확인
			if (memberService.existsByNickname(form.getNickname())) {
				bindingResult.rejectValue("nickname", "nickname.duplicate", "이미 존재하는 닉네임입니다.");
			}

			// 2-4 에러 유무 다시 확인
			if (bindingResult.hasErrors()) {
				return "/member/signup";
			}

			/* 2. 복잡한 유효성 검증 END */

			// 3. 회원 가입
		try {
			memberService.signUp(form);
		} catch (DuplicateEmailException e) {
			bindingResult.rejectValue("email", "email.duplicate", e.getMessage());
		} catch (DuplicateNickNameException e) {
			bindingResult.rejectValue("nickname", "nickname.duplicate", e.getMessage());
		} catch (Exception e) {
			log.error("알 수 없는 오류 발생: {}", e);
			bindingResult.reject("unknown", "알 수 없는 오류가 발생했습니다.");
		}

		// 3 마지막 에러 유무 확인
		if (bindingResult.hasErrors()) {
			return "/member/signup";
		}

		return "redirect:/";
	}
}
