package efub.session.community.account.domain;

import efub.session.community.account.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberTest {

        @Autowired
        private MemberRepository memberRepository;


        // success ; Member 객체가 생성된다.
        @Test
        public void testMember(){
            // given
            String email = "sojeong@gmail.com";
            String encodedPassword = "0000";
            String nickname = "soso";
            String studentId = "2122044";
            String university = "Ewha Womans University";

            // when
            Member member = new Member(email,encodedPassword,nickname,studentId,university);

            // then
            assertNotNull(member);
        }


        // fail ; 존재하지 않는 계정의 nickname을 변경한다.
        @Test
        public void updateNickname_givenInvalidMember_ReturnNullPointerException(){
            // given
            final Long INVALID_MEMBER_ID = 10L;
            String nickname = "newNickname";

            // when, then
            Member member = memberRepository.findByMemberId(INVALID_MEMBER_ID);
            NullPointerException e = assertThrows(NullPointerException.class ,
                    ()-> member.updateMember(nickname));
            assertThat(e.getMessage()).isEqualTo(null);
        }

        // fail : 계정의 memberId을 null로 변경하려는 경우
        @Test
        public void updateMember_GivenNullNickname_ReturnIllegalArgumentException(){
            // given
            final Long VALID_MEMBER_ID = 5L;

            // when, then
            Member member = memberRepository.findByMemberId(VALID_MEMBER_ID);
            // 1) 사용자 id를 null로 변경하려고 시도한다.
            // 2) 이로 인해 'member.updateMember(null)' 호출에서 IllegalArgumentException, 즉 에러가 발생한다.
            // 3) 이 경우, IllegalArgumentException을 예상하며 해당 예외가 발생하는지 test
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> member.updateMember(null));
        }

}