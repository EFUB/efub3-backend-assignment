package com.efub.community.domain.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.efub.community.domain.member.domain.MemberStatus.REGISTERED;
import static com.efub.community.domain.member.domain.MemberStatus.UNREGISTERED;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.efub.community.domain.member.dto.request.SignUpRequestDto;
import com.efub.community.domain.member.repository.MemberRepository;

class MemberTest {

	@Test
	void memberCreateTest() {
		String email = "test@naver.com";
		String nickname = "nickname";
		String encodedPassword = "encodedPassword";
		String university = "ewha";
		Integer studentId = 1976393;
		MemberStatus status = REGISTERED;

		Member member = createMember(email, nickname, encodedPassword, university,studentId);

		//testEntityManager.persist(member);

		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getNickname()).isEqualTo(nickname);
		assertThat(member.getEncodedPassword()).isEqualTo(encodedPassword);
		assertThat(member.getUniversity()).isEqualTo(university);
		assertThat(member.getStudentNo()).isEqualTo(studentId);
		assertThat(member.getStatus()).isEqualTo(status);
	}

	@Test
	void invalidEmailTest(){
		SignUpRequestDto user=SignUpRequestDto.builder()
			.email("asfd@naver.com")
			.password("encodedPwd")
			.nickname("nickname")
			.university("ewha")
			.studentNo(1976393)
			.build();
		//진행중

	}

	@Test
	void memberUpdateTest() {
		String email = "test@naver.com";
		String nickname = "nickname";
		String encodedPassword = "encodedPassword";
		String university = "ewha";
		Integer studentNo = 1976393;
		String newNickname = "newnickname";

		Member member = createMember(email, nickname, encodedPassword, university, studentNo);

		member.updateMember(newNickname);

		assertThat(member.getNickname()).isEqualTo(newNickname);

	}

	@Test
	void memberDeleteTest() {
		String email = "test@naver.com";
		String nickname = "nickname";
		String encodedPassword = "encodedPassword";
		String university = "ewha";
		Integer studentNo = 1976393;
		MemberStatus status = UNREGISTERED;

		Member member = createMember(email, nickname, encodedPassword, university,studentNo);
		member.withdraw();

		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getNickname()).isEqualTo(nickname);
		assertThat(member.getEncodedPassword()).isEqualTo(encodedPassword);
		assertThat(member.getUniversity()).isEqualTo(university);
		assertThat(member.getStudentNo()).isEqualTo(studentNo);
		assertThat(member.getStatus()).isEqualTo(status);
	}



	private Member createMember(String email, String name, String encodedPassword, String university, Integer studentNo) {
		Member member = Member.builder()
				.email(email)
				.nickname(name)
				.encodedPassword(encodedPassword)
				.university(university)

				.studentNo(studentNo)
				.build();
		return member;
	}
}
