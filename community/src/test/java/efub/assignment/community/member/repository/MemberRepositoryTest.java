package efub.assignment.community.member.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import efub.assignment.community.member.domain.Member;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void save() {
		//given
		Member member = Member.builder().email("test@email.com").password("password").nickname("nickname")
			.studentId("studentId").university("university").build();

		//when
		Member savedMember = memberRepository.save(member);

		//then
		assertThat(savedMember.getEmail()).matches("^[A-Za-z0-9+_.-]+@(.+)$");
		assertThat(savedMember.getEmail()).isEqualTo("test@email.com");
		assertThat(savedMember.getPassword()).isEqualTo("password");
		assertThat(savedMember.getNickname()).isEqualTo("nickname");
		assertThat(savedMember.getNickname()).hasSizeLessThanOrEqualTo(16);
		assertThat(savedMember.getUniversity()).isEqualTo("university");
		assertThat(savedMember.getStudentId()).isEqualTo("studentId");
	}

	@Test
	public void existsByEmail(){
		//given
		String email = "test@email.com";
		Member member = Member.builder().email(email).password("password").nickname("nickname")
			.studentId("studentId").university("university").build();
		memberRepository.save(member);

		//when
		Boolean exist = memberRepository.existsByEmail(email);

		//then
		assertTrue(exist);
	}

	@Test
	public void existsByStudentId(){
		//given
		String studentId = "2176082";
		Member member = Member.builder().email("test@email.com").password("password").nickname("nickname")
			.studentId(studentId).university("university").build();
		memberRepository.save(member);

		//when
		Boolean exist = memberRepository.existsByStudentId(studentId);

		//then
		assertTrue(exist);
	}

}