package com.efub.community.domain.member.repository;

import com.efub.community.domain.member.domain.Member;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.validation.constraints.Email;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE, connection = EmbeddedDatabaseConnection.H2)
class MemberRepositoryTest { // repsoitory단에서 dto에서 하는 이메일 형식 검증을 하려니까 어색한 것 같아서, 다음주 과저에서 하도록 하겠습니다..
    @Autowired
    private MemberRepository memberRepository;

    private Member saveMember;
    private Integer studentNo;
    private String nickname;

    @BeforeEach
    public void setup() throws Exception{
        studentNo = 20210433;
        nickname = "young";
        saveMember = memberRepository.saveAndFlush(Member.builder()
                .email("gayoung@gmail.com")
                .encodedPassword("password1")
                .nickname(nickname)
                .university("Ewha Womans University")
                .studentNo(studentNo)
                .build());

    }

    @Test
    @DisplayName("studentNo로 유저를 조회한다.")
    void findByStudentNoTest(){
        Member foundMember = memberRepository.findByStudentNo(studentNo).orElse(null);
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getStudentNo()).isEqualTo(studentNo);
    }

    @Test
    @DisplayName("studentNo로 유저 존재 여부를 확인한다.")
    void existByStudentNoTest(){
        final boolean existsByStudentNo = memberRepository.existsByStudentNo(studentNo);
        assertThat(existsByStudentNo).isTrue();
    }

    @Test
    @DisplayName("nickname으로 유저 존재 여부를 확인한다.")
    void existByNicknameTest(){
        final boolean existsByNickname = memberRepository.existsByNickname(nickname);
        assertThat(existsByNickname).isTrue();
    }

    @Test
    @DisplayName("email은 null이 될 수 없다.")
    void emailNotNullabeTest(){
        Member member1 = memberRepository.saveAndFlush(Member.builder()
                .encodedPassword("password1")
                .nickname(nickname)
                .university("Ewha Womans University")
                .studentNo(studentNo)
                .build());

        assertNotNull(member1.getEmail());
    }

}