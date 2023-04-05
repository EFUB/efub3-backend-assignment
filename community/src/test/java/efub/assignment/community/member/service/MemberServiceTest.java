package efub.assignment.community.member.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberUpdateRequestDto;
import efub.assignment.community.member.dto.SignUpRequestDto;
import efub.assignment.community.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @BeforeEach
    void beforeEach(){ memberRepository.deleteAll(); }

    @Test
    @DisplayName("create member")
    public void testSignUp() {
        //given
        String email = "email@naver.com";
        String password = "password";
        String nickname = "nickname";
        String university = "이화여자대학교";
        String studentId = "2176082";
        SignUpRequestDto requestDto = SignUpRequestDto.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .university(university)
                .studentId(studentId)
                .build();

        //when
        Long memberId = memberService.signUp(requestDto);

        //then
        Member member = memberRepository.findById(memberId).orElse(null);
        assertNotNull(member);
        assertEquals(requestDto.getEmail(), member.getEmail());
        assertEquals(requestDto.getNickname(), member.getNickname());
        assertEquals(requestDto.getUniversity(), member.getUniversity());
        assertEquals(requestDto.getStudentId(), member.getStudentId());
    }

    @Test
    @DisplayName("update member profile")
    public void testUpdate() {
        //given
        Member member = Member.builder()
                .email("email@naver.com")
                .password("Password!")
                .nickname("nickname")
                .university("이화여자대학교")
                .studentId("2176082")
                .build();
        memberRepository.save(member);
        Long memberId = member.getMemberId();

        String nickname = "new_nickname";
        MemberUpdateRequestDto requestDto = MemberUpdateRequestDto.builder()
                .nickname(nickname)
                .build();

        //when
        Long updatedMemberId = memberService.update(memberId, requestDto);

        //then
        assertEquals(memberId, updatedMemberId);
        Member updateMember = memberRepository.findById(memberId).orElse(null);
        assertNotNull(updateMember);
        assertEquals(requestDto.getNickname(), updateMember.getNickname());
    }

    @Test
    @DisplayName("withdraw member")
    public void withdraw() {
        //given
        Member member = Member.builder()
                .email("email@naver.com")
                .password("Password!")
                .nickname("nickname")
                .university("이화여자대학교")
                .studentId("2176082")
                .build();
        memberRepository.save(member);
        Long memberId = member.getMemberId();

        //when
        memberService.withdraw(memberId);

        //then
        Member deletedMember = memberRepository.findById(memberId).orElse(null);
        assertNotNull(deletedMember);
    }
}