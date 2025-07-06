package com.sbs.locally.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sbs.locally.member.entity.Member;
import com.sbs.locally.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

	private final MemberRepository memberRepository;
	
	public String findEmail(String email) {
		
		Optional<Member> member = memberRepository.findByEmail(email);
		
		return member.map(Member::getEmail).orElse("");
	}
}
