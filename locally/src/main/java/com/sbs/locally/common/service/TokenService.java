package com.sbs.locally.common.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sbs.locally.common.entity.VerificationToken;
import com.sbs.locally.common.enums.TokenType;
import com.sbs.locally.common.repository.TokenRepository;
import com.sbs.locally.member.entity.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;
	
	public boolean isValidToken(String token) {
		
		return tokenRepository.findByToken(token).map(t -> !t.isExpired()).orElse(false);
	}

	public VerificationToken createToken(Member member, TokenType tokenType) {

		// 기존 토큰 여부
		// 기존 토큰 기록 남기기
		// 0. 최초 토큰 생성
		// 1. 사용자가 메일 받고 비밀번호 변경했을 경우: used: true, usedAt: LocalDateTime.now() --> 여기서
		// 설정하지 않음
		// 2. 사용자가 메일을 또 전송하지 않고 그냥 냅둠 : 아무 처리 x --> 여기서 설정하지 않음
		// 3. 사용자가 메일 재전송
		// 3-1. 유효기간 내에 재전송 한 경우: expiryDate: LocalDateTime.now()
		// 3-2. 유효기간 후에 재전송 한 경우: 그냥 재전송

		// 기존에 토큰이 존재하는지 확인 (유효한 토큰은 단 한 개여야 함!)
		// 1. 토큰이 존재하나요?
		// 2. 이 토큰의 유효기간이 지났나요?
		// --> 만약, 유효기간이 지난 경우 재전송 (상관없음)
		// --> 만약, 유효기간이 지나지 않았을 경우에만 토큰을 수정..
		// 유효기간 내의 토큰은 단 한 개!

		Optional<VerificationToken> validToken = tokenRepository.findByMemberAndExpiryDateAfter(member,
				LocalDateTime.now());

		log.info("토큰? {}", validToken);
		if (validToken.isPresent()) {
			VerificationToken updatedToken = validToken.get();

			// 유효기간 설정 변경
			updatedToken.setExpiryDate(LocalDateTime.now());
			tokenRepository.save(updatedToken);
		}

		String uuid = UUID.randomUUID().toString();

		LocalDateTime expiryDate = (tokenType == TokenType.RESET_PASSWORD) ? LocalDateTime.now().plusMinutes(30) : LocalDateTime.now().plusHours(24);
		
		VerificationToken token = VerificationToken.builder().type(tokenType).token(uuid).member(member)
				.expiryDate(expiryDate).build();

		tokenRepository.save(token);

		return token;
	}
}
