package com.sbs.locally.member.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sbs.locally.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDeleteService {

	private final MemberRepository memberRepository;
	
	@Scheduled(cron = "0 0 3 * * *")
	public void deleteUnabledMembers() {
		
	}
}
