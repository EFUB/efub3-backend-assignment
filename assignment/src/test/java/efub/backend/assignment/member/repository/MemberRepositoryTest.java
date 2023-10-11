package efub.backend.assignment.member.repository;

import efub.backend.assignment.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,
	connection = EmbeddedDatabaseConnection.H2)
public class MemberRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void saveMemberSuccess() { // 성공한 테스트케이스
		Member member = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();
		testEntityManager.persist(member);

		assertThat(memberRepository.existsByEmail(member.getEmail())).isTrue();
	}

	@Test
	public void invalidEmail() { // 이메일 형식과 관련한 실패한 테스트 케이스
		Member member = Member.builder()
			.email("chaewon1019ewhain..net") // 이메일의 형식이 잘못되었음
			.password("chaewon1019!!!")
			.nickname("채원")
			.university("이화여자대학교")
			.studentId("2076216")
			.build();
	    testEntityManager.persist(member);

		assertThat(memberRepository.existsByEmailLike("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")).isTrue();
	}

	@Test
	public void invalidEmailLength() { // 닉네임 길이와 관련한 실패한 테스트 케이스
		Member member = Member.builder()
			.email("chaewon1019@ewhain.net")
			.password("chaewon1019!!!")
			.nickname("chaewonononononono") // 닉네임 길이 형식이 잘못되었음
			.university("이화여자대학교")
			.studentId("2076216")
			.build();

		assertThrows(Exception.class, () -> {
			testEntityManager.persist(member);
			testEntityManager.flush();  // 예외 발생
		});
	}

}
