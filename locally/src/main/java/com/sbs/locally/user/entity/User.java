package com.sbs.locally.user.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.sbs.locally.user.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique=true)
	private String nickname;
	
	private String profileImage;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	private String provider;
	
	private String providerId;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@CreationTimestamp
	private LocalDateTime updatedAt;
}
