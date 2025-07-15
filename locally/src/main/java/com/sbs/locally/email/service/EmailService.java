package com.sbs.locally.email.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sbs.locally.common.entity.VerificationToken;
import com.sbs.locally.member.entity.Member;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;

	@Async
	public void sendVerificationEmail(Member member, VerificationToken token) {

		try {
			log.info("이메일 전송 중... 이메일: {}, 토큰: {}", member.getEmail(), token.getToken());

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			String tokenUrl = "http://localhost:8080/auth/resetPassword?token=" + token.getToken();

			/*
			 * String contentText = """ <b>Locally</b> 비밀번호 재설정 이메일입니다. <br><br> 아래 버튼을 눌러
			 * 인증을 완료해주세요.<br><br> <a href="%s">비밀번호 재설정하기</a><br>
			 * 
			 * """;
			 */
			// log.info(contentText);

			helper.setTo(member.getEmail());

			helper.setSubject("Locally 비밀번호 찾기");
			helper.setText(String.format(
					"<b>Locally</b> 비밀번호 재설정 이메일입니다.. <br><br> 아래 버튼을 눌러 인증을 완료해주세요.<br><br> <a href=\"%s\">비밀번호 재설정하기</a><br>",
					tokenUrl), true); // true -> HTML

			javaMailSender.send(message);
		} catch (Exception e) {
			log.error("메일 전송 중 오류 발생", e.getMessage());
		}

	}
}
