package com.sbs.locally.member.service;

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
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(MemberRole.ADMIN == member.getRole()) {
			authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue())); // if 문에서는 enum 끼리 비교했지만, 스프링 시큐리티가 인식할 때는 String!!
		} else {
			authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
		}
		
		return new User(member.getEmail(), member.getPassword(), authorities);
	}
	
	
}
