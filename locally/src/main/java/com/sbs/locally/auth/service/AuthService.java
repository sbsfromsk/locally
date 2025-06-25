package com.sbs.locally.auth.service;

import org.springframework.stereotype.Service;

import com.sbs.locally.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

	private final MemberRepository memberRepository;
}
