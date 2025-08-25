package com.sbs.locally.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sbs.locally.CustomUserDetails;
import com.sbs.locally.common.exception.UserNotActivatedException;
import com.sbs.locally.member.entity.Member;
import com.sbs.locally.member.enums.MemberRole;
import com.sbs.locally.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = this.memberRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
		
		log.info("로그인 중...");
		/*
		 * 
				org.springframework.security.core.userdetails.User.User (
				String username, 
				String password, 
				boolean enabled, 
				boolean accountNonExpired, 
				boolean credentialsNonExpired, 
				boolean accountNonLocked, 
				Collection <? extends GrantedAuthority> authorities)
		 * 
		 * */
		return new CustomUserDetails(member);
	}
	
	
}
