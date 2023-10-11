package com.efub.community.domain.member.repository;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "nickname", 1, "university");

        // when
        final Member savedMember = memberRepository.save(member);

        // then
        assertEquals("test@email.com", savedMember.getEmail());
        assertEquals("test", savedMember.getNickname());
        assertEquals("nickname", savedMember.getNickname());
        assertEquals("university", savedMember.getUniversity());
    }

    @Test
    void findByStudentNo() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "nickname", 1, "university");

        // when
        final Optional<Member> findByStudentNoResult = memberRepository.findByStudentNo(1);

        // then
        assertTrue(findByStudentNoResult.isPresent());
    }

    @Test
    void existsByStudentNo() {
        // given
        final Member member = new Member("test@email.com", "teST1!", "nickname", 1, "university");

        //when
        final Boolean existsByStudentNoResult = memberRepository.existsByStudentNo(1);

        // then
        assertTrue(existsByStudentNoResult);
    }
}