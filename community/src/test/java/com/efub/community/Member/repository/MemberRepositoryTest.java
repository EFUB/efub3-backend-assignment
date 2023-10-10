package com.efub.community.Member.repository;

import com.efub.community.Member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    private MemberRepository memberRepositoryMock;

    @BeforeEach
    public void setUp() {
        // 테스트 데이터 생성
        Member member = new Member("test@example.com", "password123", "TestUser", "Test University", "12345");

        // Mock 객체 설정
        Mockito.when(memberRepositoryMock.existsByEmail("test@example.com")).thenReturn(true);
        Mockito.when(memberRepositoryMock.findMemberByEmail("test@example.com")).thenReturn(Optional.of(member));
    }

    @Test
    public void testExistsByEmail() {
        // 이메일이 존재하는지 확인
        assertTrue(memberRepository.existsByEmail("test@example.com"));
    }

    @Test
    public void testFindMemberByEmail() {
        // 이메일로 멤버를 찾아와서 정보를 확인
        Optional<Member> foundMember = memberRepository.findMemberByEmail("test@example.com");
        assertTrue(foundMember.isPresent());

        Member member = foundMember.get();
        assertEquals("test@example.com", member.getEmail());
        assertEquals("password123", member.getPw());
        assertEquals("TestUser", member.getNickname());
        assertEquals("Test University", member.getUniv());
        assertEquals("12345", member.getStudentId());
    }

    @Test
    public void testExistsByEmailNonExistentEmail() {
        // 존재하지 않는 이메일로 existsByEmail 호출
        assertFalse(memberRepository.existsByEmail("nonexistent@example.com"));
    }

    @Test
    public void testFindMemberByEmailNonExistentEmail() {
        // 존재하지 않는 이메일로 findMemberByEmail 호출
        Optional<Member> foundMember = memberRepository.findMemberByEmail("nonexistent@example.com");
        assertFalse(foundMember.isPresent());
    }
}