package com.efub.community.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efub.community.domain.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByMemberId(Long memberId);

	Optional<Member> findByStudentNo(Integer studentNo);

	Boolean existsByStudentNo(Integer studentNo);

	Boolean existsByNickname(String nickname);

	Optional<Member> findByEmail(String email);
}
