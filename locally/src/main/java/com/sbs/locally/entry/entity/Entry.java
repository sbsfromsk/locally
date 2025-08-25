package com.sbs.locally.entry.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.sbs.locally.entry.enums.EntryStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	private String content;
	
	private String author;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	//private List<PostImage> images = new ArrayList<>();
	
	// private Long viewCount = 0L;
	
	@Enumerated(EnumType.STRING)
	private EntryStatus status;
}
