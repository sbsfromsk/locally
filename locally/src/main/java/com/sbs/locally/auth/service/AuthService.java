package com.sbs.locally.auth.service;

import java.util.Optional;

import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.AssertingParty.Verification;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sbs.locally.auth.forms.ResetPasswordForm;
import com.sbs.locally.common.entity.VerificationToken;
import com.sbs.locally.common.enums.TokenType;
import com.sbs.locally.common.exception.InvalidTokenException;
import com.sbs.locally.common.service.TokenService;
import com.sbs.locally.email.service.EmailService;
import com.sbs.locally.member.entity.Member;
import com.sbs.locally.member.repository.MemberRepository;
import com.sbs.locally.member.service.MemberService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

	private final EmailService emailService;
	private final TokenService tokenService;

	private final MemberRepository memberRepository;

	private final JavaMailSender javaMailSender;

	public Optional<Member> findEmail(String email) {

		return memberRepository.findByEmail(email);

	}

	public void sendResetPasswordEmail(String email, TokenType type) throws MessagingException {
		log.info("비밀번호 재설정 메일 보내는 중...");

		// 1.사람 찾기
		// 2. 없으면 그냥 그대로 void 직행
		// 3. 있으면 Token 만들기
		// 4. 토큰과 함께 이메일 서비스로 전달
		// 5. 이메일 서비스에서 메일 전송

		Optional<Member> member = findEmail(email);

		if (member.isPresent()) {
			Member toMember = member.get();
			VerificationToken token = tokenService.createToken(toMember, type);

			log.info("토큰: {}", type);
			if (type == TokenType.RESET_PASSWORD) {
				emailService.sendResetPasswordEmail(toMember, token);
			} else {
				emailService.sendSignUpEmail(toMember, token);
			}

		}
	}

	/**
	 * 비밀번호 설정 1. 토큰 유효 (TokenService) 2. 회원 찾기 (MemeberService) 3. 회원 비밀번호 바꾸기
	 * (MemberService) 4. 토큰 만료!(uesdDate, used 체크)
	 * TODO
	 */
	public void resetPassword(String passwordToken, String password) {

		if (!tokenService.isValidToken(passwordToken)) {
			throw new InvalidTokenException();
		}
		;
	}

	/**
	 * 1. Member 객체 enabled = True
	 * 2. 토큰 만료!
	 * */
	public void activateMember(String token) {
		
		// 1. 토큰 유효 다시 한 번 확인
		// 2. 토큰 enabled...
		// 3. 토큰 만료하기
		
	}
}
