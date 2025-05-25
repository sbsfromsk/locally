package com.sbs.locally.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.locally.post.dto.PostCreateForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Controller
@Slf4j
public class PostController {

	@GetMapping("/post")
	public String createPost() {
		return "/post/postCreate";
	}
	
	@PostMapping("/post")
	public String createPost(@Valid PostCreateForm postCreateForm) {
		return "/post/postList";
	}
}
