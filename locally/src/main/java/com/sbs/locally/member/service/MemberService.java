package com.sbs.locally.member.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbs.locally.common.exception.DuplicateEmailException;
import com.sbs.locally.common.exception.DuplicateNickNameException;
import com.sbs.locally.member.entity.Member;
import com.sbs.locally.member.enums.MemberRole;
import com.sbs.locally.member.forms.SignupForm;
import com.sbs.locally.member.repository.MemberRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public boolean existsByEmail(String email) {
		
		return memberRepository.existsByEmail(email); 
	}

	public boolean existsByNickname(String nickname) {
		
		return memberRepository.existsByNickname(nickname);
	}
	
	@Transactional
	public void signUp(SignupForm form) {
		
		// 1. 다시 한번 중복 검증
		validateDuplicate(form);
		
		
		// 2. 비밀번호 암호화
		String encodedPassword = passwordEncoder.encode(form.getPassword1());
		
		// 3. 회원 객체 생성
		Member member = Member.builder()
						.email(form.getEmail())
						.password(encodedPassword)
						.nickname(form.getNickname())
						.role(MemberRole.USER)
						.build();
		
		//4. 저장
		try {
		memberRepository.save(member);
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("email")) {
				throw new DuplicateEmailException("DB 이미 사용중인 이메일입니다.");
			} else if (e.getMessage().contains("nickname")) {
				throw new DuplicateNickNameException("DB 이미 존재하는 닉네임입니다.");
			}
			throw e;
		}
	}

	private void validateDuplicate(SignupForm form) {
		if (existsByEmail(form.getEmail())) {
			throw new DuplicateEmailException("이미 사용중인 이메일입니다.");
		}
		
		if (existsByNickname(form.getNickname())) {
			throw new DuplicateNickNameException("이미 사용중인 닉네임입니다.");
		}
		
	}
}
