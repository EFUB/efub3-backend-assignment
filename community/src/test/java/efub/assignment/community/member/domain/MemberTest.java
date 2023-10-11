package efub.assignment.community.member.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MemberTest {

	@Test
	public void testMember_wrongEmail(){
		//given
		String email = "test email";
		String password = "test password";
		String nickname = "test nickname";
		String university = "test university";
		String studentId = "test studentId";

		//when
		Member member = Member.builder().email(email).password(password).nickname(nickname)
			.university(university).studentId(studentId).build();

		//then
		assertThat(member.getEmail()).matches("^[A-Za-z0-9+_.-]+@(.+)$");
		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getPassword()).isEqualTo(password);
		assertThat(member.getNickname()).isEqualTo(nickname);
		assertThat(member.getUniversity()).isEqualTo(university);
		assertThat(member.getStudentId()).isEqualTo(studentId);
	}

	@Test
	public void testMember_wrongNicknameLength(){
		//given
		String email = "test@email.com";
		String password = "test password";
		String nickname = "test long long long long nickname";
		String university = "test university";
		String studentId = "test studentId";

		//when
		Member member = Member.builder().email(email).password(password).nickname(nickname)
			.university(university).studentId(studentId).build();

		//then
		assertThat(member.getEmail()).matches("^[A-Za-z0-9+_.-]+@(.+)$");
		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getPassword()).isEqualTo(password);
		assertThat(member.getNickname()).isEqualTo(nickname);
		assertThat(member.getNickname()).hasSizeLessThanOrEqualTo(16);
		assertThat(member.getUniversity()).isEqualTo(university);
		assertThat(member.getStudentId()).isEqualTo(studentId);
	}

	@Test
	public void testMember_create(){
		//given
		String email = "test@email.com";
		String password = "test password";
		String nickname = "test nickname";
		String university = "test university";
		String studentId = "test studentId";

		//when
		Member member = Member.builder().email(email).password(password).nickname(nickname)
			.university(university).studentId(studentId).build();

		//then
		assertThat(member.getEmail()).matches("^[A-Za-z0-9+_.-]+@(.+)$");
		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getPassword()).isEqualTo(password);
		assertThat(member.getNickname()).isEqualTo(nickname);
		assertThat(member.getNickname()).hasSizeLessThanOrEqualTo(16);
		assertThat(member.getUniversity()).isEqualTo(university);
		assertThat(member.getStudentId()).isEqualTo(studentId);
	}


	@Test
	public void updateMember() {
		//given
		Member member = Member.builder().email("test@email.com").password("test password").nickname("test nickname")
			.studentId("test studentId").university("test university").build();

		//when
		member.updateMember("new nickname");

		//then
		assertEquals(member.getNickname(), "new nickname");
	}

	@Test
	public void withdrawMember() {
		//given
		Member member = Member.builder().email("test@email.com").password("test password").nickname("test nickname")
			.studentId("test studentId").university("test university").build();

		//when
		member.withdrawMember();

		//then
		assertEquals(member.getStatus(), MemberStatus.UNREGISTERED);
	}
}