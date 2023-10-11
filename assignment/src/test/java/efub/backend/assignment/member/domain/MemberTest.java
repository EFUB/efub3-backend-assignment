package efub.backend.assignment.member.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class MemberTest {

	private TestEntityManager testEntityManager;

	@Test
	public void successMemberTest() { // 성공한 테스트 코드
		Member member = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();

		assertThat(member.getEmail()).isEqualTo("chaewon1019@ewhain.net");
		assertThat(member.getEncodedPassword()).isEqualTo("chaewon1019!!!");
		assertThat(member.getNickname()).isEqualTo("채원");
		assertThat(member.getUniversity()).isEqualTo("이화여자대학교");
		assertThat(member.getStudentId()).isEqualTo("2076216");
		assertThat(member.getStatus()).isEqualTo(MemberStatus.REGISTERED);
	}

	@Test
	public void failMemberTest_Length() { // 실패한 테스트 코드
		String nickname = "chaewononononononononon";  // 16자를 초과하는 닉네임

		assertThrows(Exception.class, () -> {
			Member member = Member.builder()
				.email("chaewon1019@ewhain.net")
				.password("chaewon1019!!!")
				.nickname(nickname)
				.university("이화여자대학교")
				.studentId("2076216")
				.build();

			testEntityManager.persist(member);  // 데이터베이스에 Member 객체 저장하면서 예외가 발생!
			testEntityManager.flush();
		});
	}

	@Test
	public void failMemberTest_NULL() { // 실패한 테스트 코드

		assertThrows(Exception.class, () -> {
			Member member = Member.builder()
				// email은 nullable=false인데 입력하지 않음
				.password("chaewon1019!!!")
				.nickname("채원")
				.university("이화여자대학교")
				.studentId("2076216")
				.build();

			testEntityManager.persist(member);  // 데이터베이스에 Member 객체 저장하면서 예외가 발생!
			testEntityManager.flush();
		});
	}
}
