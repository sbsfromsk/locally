package com.sbs.locally.common.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbs.locally.common.entity.VerificationToken;
import com.sbs.locally.common.enums.TokenType;
import com.sbs.locally.member.entity.Member;

public interface TokenRepository extends JpaRepository<VerificationToken, Long> {

	
	Optional<VerificationToken> findByMemberAndExpiryDateAfter(Member member, LocalDateTime time);

	Optional<VerificationToken> findByMemberAndExpiryDateBefore(Member member, LocalDateTime time);

	Optional<VerificationToken> findByToken(String token);

	List<VerificationToken> findByMemberIn(List<Member> members);

	List<VerificationToken> findByUsedFalseAndTypeAndMemberIn(TokenType signUp, List<Member> members);

	List<VerificationToken> findByMemberInAndTypeAndUsedFalseAndExpiryDateBefore(List<Member> members, TokenType signUp,
			LocalDateTime now);
}
