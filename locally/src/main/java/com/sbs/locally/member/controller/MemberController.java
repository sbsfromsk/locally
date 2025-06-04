package com.sbs.locally.member.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.locally.member.forms.SignupForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/signup")
	public String signup() {
		
		return "/member/signup";
	}
	
	@PostMapping("/signup")
	public String signup2(SignupForm signupForm) {
		
		return "redirect:/";
	}
}
