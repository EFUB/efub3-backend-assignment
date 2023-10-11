package efub.session.blog.member;

import efub.session.blog.member.domain.Member;
import efub.session.blog.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class MemberRepositoryTest {

    private MemberService memberService;
    
    @DisplayName("성공: member_id로 member 조회하기")
    @Test
    public void find_member_by_memberId(){
        //given
        Long studentId= 1234567L;
        String school="이화여자대학교";
        String nickname="퍼비";
        String email="efub@naver.com";
        String encodedPassword="111222";
        String bio="안녕하세요!";
        Member member = new Member(studentId, school, nickname, email, encodedPassword, bio);

        Long memberId=1L;

        //when
        assertThat(memberService.findMemberById(memberId).getMemberId()).isEqualTo(memberId);
    }

    @DisplayName("실패: 존재하지 않는 member_id로 member 조회하기")
    @Test
    public void find_member_by_wrong_memberId(){
        //given
        Long memberId=2L;

        //when
        assertThat(memberService.findMemberById(memberId).getMemberId()).isEqualTo(memberId);
    }
}
