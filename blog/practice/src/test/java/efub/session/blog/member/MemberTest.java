package efub.session.blog.member;

import efub.session.blog.member.domain.Member;
import efub.session.blog.member.dto.SignUpRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
public class MemberTest {

    @DisplayName("성공: member 객체 생성")
    @Test
    public void testMember(){
        //given
        Long studentId= 1234567L;
        String school="이화여자대학교";
        String nickname="퍼비";
        String email="efub@naver.com";
        String encodedPassword="111222";
        String bio="안녕하세요!";

        //when
        Member member = new Member(studentId, school, nickname, email, encodedPassword, bio);

        //then
        assertNotNull(member);
    }

    @DisplayName("실패: member생성시 비밀번호 형식 못지킴")
    @Test
    void password_wrong_when_member_create(){
        //given
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto(1234567L, "이화여자대학교", "퍼비", "efub@naver.com", "a123");

    }


}
