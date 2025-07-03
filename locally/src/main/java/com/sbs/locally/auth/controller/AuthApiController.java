package com.sbs.locally.auth.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthApiController {

	@PostMapping("/password")
	public ResponseEntity<Void> findPassword(@RequestBody Map<String, String> email) {
		log.info("쿠쿠키쿠쿠키");
		
		return ResponseEntity.noContent().build();
	}
}
