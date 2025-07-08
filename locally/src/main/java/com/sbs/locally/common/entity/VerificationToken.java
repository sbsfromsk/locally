package com.sbs.locally.common.entity;

import java.time.LocalDateTime;

import com.sbs.locally.member.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VerificationToken {
	// TODO: 이메일 + 비밀번호 재설정 구분 enum 만들기
	// TODO: LocalDateTime 노션 ...
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String token;
	
	@OneToOne
	private Member member;
	
	@Column(nullable = false)
	private LocalDateTime expiryDate;
	
	public VerificationToken(String token, Member member, int expiryMinutes) {
		this.token = token;
		this.member = member;
		this.expiryDate = LocalDateTime.now().plusMinutes(expiryMinutes);
	}
	
	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expiryDate);
	}
	
}
