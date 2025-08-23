package com.sbs.locally.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbs.locally.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);
	
	Optional<Member> findByEmail(String email);

	List<Member> findByEnabledFalse();

}
