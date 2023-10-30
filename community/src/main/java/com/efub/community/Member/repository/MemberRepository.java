package com.efub.community.Member.repository;

import com.efub.community.Member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByEmail(String email);
    Optional<Member> findMemberByEmail(String email);
}
