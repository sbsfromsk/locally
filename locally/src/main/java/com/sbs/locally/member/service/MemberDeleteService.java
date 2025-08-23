package com.sbs.locally.member.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sbs.locally.common.entity.VerificationToken;
import com.sbs.locally.common.enums.TokenType;
import com.sbs.locally.common.repository.TokenRepository;
import com.sbs.locally.member.entity.Member;
import com.sbs.locally.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/***
 * 미인증 회원 7일이 지나면 DB에서 지우는 스케쥴
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDeleteService {

	private final MemberRepository memberRepository;
	private final TokenRepository tokenRepository;
	
	
	/***
	 * 1. Member 데이터에서, enabled=false인 회원을 찾는다
	 * 2. 토큰에서는 USED=false
	 * 3. 회원가입 토큰타입: SIGN_UP
	 * 4. expiry_date가 현재보다 지난 경우를 찾는다
	 * 3. Member DB에서 데이터를 제거한다.
	 */
	@Scheduled(cron = "0 0 0 * * *")
	public void deleteUnabledMembers() {
		List<Member> members = memberRepository.findByEnabledFalse();
		
		//List<Integer> members = memberRepository.findByEnabledFalse().stream().map(m -> m.getId()).collect(Collectors.toList());
		
		List<VerificationToken> tokens = tokenRepository.findByMemberInAndTypeAndUsedFalseAndExpiryDateBefore(members, TokenType.SIGN_UP, LocalDateTime.now());
		List<Member> deleteMembers = tokens.stream().map(t -> t.getMember()).distinct().collect(Collectors.toList());
		log.info("tokens: {}", tokens);
		
		tokenRepository.deleteAll(tokens);
	
		tokenRepository.flush();
		
		memberRepository.deleteAll(deleteMembers);
		
		log.info("삭제 완료!");
		
	}
	
	
}
