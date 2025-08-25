package com.sbs.locally.entry.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.locally.CustomUserDetails;
import com.sbs.locally.entry.dto.EntryCreateForm;
import com.sbs.locally.entry.entity.Entry;
import com.sbs.locally.entry.service.EntryService;
import com.sbs.locally.member.entity.Member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Controller
@RequestMapping("/entry")
@Slf4j
public class EntryController {
	
	private final EntryService entryService;

	/***
	 * 내가 작성한 엔트리 목록 페이지
	 * 1. 내가 작성한 엔트리 정보 가져오기
	 * 2. 엔트리 뿌리기
	 * @return
	 */
	@GetMapping("/myEntry")
	public String entryList(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		
		// 1. 내가 작성한 엔트리 가져오기
		log.info("사용자 정보: {}", userDetails.getMember().getId());
		List<Entry> entries = entryService.findById(userDetails.getMember().getId());
		
		// 2. View에 전달하기
		model.addAttribute("entries", entries);
		
		return "/entry/entryList";
	}
	
	@GetMapping("/create")
	public String createEntry() {
		return "/entry/createEntry";
	}
	
	@PostMapping("/create")
	public String createEntry(@Valid EntryCreateForm entryCreateForm) {
		return "/entry/createEntry";
	}
}
