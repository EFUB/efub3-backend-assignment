package com.efub.community.Member.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class MemberTest {
    private Member member;

    @BeforeEach
    // Member 객체를 생성하여 초기화
    public void setUp() {
        member = Member.builder()
                .email("test@example.com")
                .pw("password123")
                .nickname("TestUser")
                .univ("Test University")
                .studentId("12345")
                .build();
    }

    @Test
    public void testMemberCreation() {
        // 1. Member 객체가 null이 아닌지 확인
        assertNotNull(member);

        // 2. Member 객체의 필드 값들이 예상한 대로 설정되었는지 확인
        assertEquals("test@example.com", member.getEmail());
        assertEquals("password123", member.getPw());
        assertEquals("TestUser", member.getNickname());
        assertEquals("Test University", member.getUniv());
        assertEquals("12345", member.getStudentId());

        // 3. Member 객체의 status가 올바르게 설정되었는지 확인
        assertEquals(Status.REGISTERED, member.getStatus());
    }

}