package com.sbs.locally.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbs.locally.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);

}
