package com.sbs.locally.email.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;
	
	public void sendEmail(String email) throws MessagingException {
		log.info("메일 보내는 중...");
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper =  new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setTo(email);
		helper.setSubject("Locally 비밀번호 찾기");
		helper.setText("<b>Locally 메일 테스트</b>", true); // true -> HTML
		
		javaMailSender.send(message);
	}
}
