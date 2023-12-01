package com.efub.community.domain.member.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.efub.community.domain.member.MemberDomainTest;

class MemberTest extends MemberDomainTest {

	@Test
	@DisplayName("계정을 생성합니다.")
	void create() {
		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getEncodedPassword()).isEqualTo(encodedPassword);
		assertThat(member.getUniversity()).isEqualTo(university);
		assertThat(member.getStudentNo()).isEqualTo(studentNo);
		assertThat(member.getNickname()).isEqualTo(nickname);
		assertThat(member.getStatus()).isEqualTo(registered);
	}

	@Test
	@DisplayName("계정 생성 시 잘못된 형식의 이메일을 입력하면, 예외가 발생합니다.")
	void createWithInvalidEmail() {
		String invalidEmail = "email";
		Assertions.assertThatIllegalArgumentException()
			.isThrownBy(() -> Member.builder()
				.email(invalidEmail)
				.nickname(nickname)
				.encodedPassword(encodedPassword)
				.university(university)
				.studentNo(studentNo)
				.build());
	}

	@Test
	@DisplayName("계정을 업데이트합니다.")
	void update() {
		// given
		String newNickname = "새테스트계정";
		// when
		member.updateMember(newNickname);
		// then
		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getEncodedPassword()).isEqualTo(encodedPassword);
		assertThat(member.getUniversity()).isEqualTo(university);
		assertThat(member.getStudentNo()).isEqualTo(studentNo);
		assertThat(member.getNickname()).isEqualTo(newNickname);
		assertThat(member.getStatus()).isEqualTo(registered);
	}

	@Test
	@DisplayName("비활성화된 계정을 업데이트하면, 예외가 발생합니다.")
	void updateUnregisteredMember() {
		member.withdraw();
		Assertions.assertThatIllegalArgumentException()
			.isThrownBy(() -> member.updateMember("새 닉네임"));
	}

	@Test
	@DisplayName("계정을 삭제하면, status가 UNREGISTERED로 변경됩니다.")
	void withdraw() {
		// given
		// when
		member.withdraw();
		// then
		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getEncodedPassword()).isEqualTo(encodedPassword);
		assertThat(member.getUniversity()).isEqualTo(university);
		assertThat(member.getStudentNo()).isEqualTo(studentNo);
		assertThat(member.getNickname()).isEqualTo(nickname);
		assertThat(member.getStatus()).isEqualTo(unregistered);
	}

	@Test
	@DisplayName("비활성화된 계정을 삭제하면, 예외가 발생합니다.")
	void withdrawUnregisteredMember() {
		member.withdraw();
		Assertions.assertThatIllegalArgumentException()
			.isThrownBy(member::withdraw);
	}

}
