package com.sbs.locally.member.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.locally.member.forms.SignupForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/signup")
	public String signup(SignupForm signupForm) {
		
		return "/member/signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute SignupForm form, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			//model.addAttribute("errors", bindingResult.getAllErrors());
			return "/member/signup";
		}
		
		log.info("사용자의 이메일: {}", form.getEmail());
		log.info("사용자의 닉네임: {}", form.getNickname());
		log.info("사용자의 비밀번호: {}", form.getPassword1());
		return "redirect:/";
	}
}
