package com.efub.community.domain.member.domain;

import com.efub.community.domain.member.dto.request.SignUpRequestDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static com.efub.community.domain.member.domain.MemberStatus.REGISTERED;
import static com.efub.community.domain.member.domain.MemberStatus.UNREGISTERED;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemberTest {
	private static ValidatorFactory factory;
	private static Validator validator;

	@BeforeAll
	public static void init() {
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void close() {
		factory.close();
	}

	@Test
	void 이메일_형식_유효성_실패_테스트() {
		// given
		SignUpRequestDto signUpRequestDto = new SignUpRequestDto("test", "testTEST1!", "test", "test", 1);

		// when
		Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(signUpRequestDto);

		// then
		violations
				.forEach(error -> {
					assertThat(error.getMessage()).isEqualTo("유효하지 않은 이메일 형식입니다.");
				});
	}

	@Test
	void 비밀번호_형식_유효성_실패_테스트() {
		// given
		SignUpRequestDto signUpRequestDto = new SignUpRequestDto("test@email.com", "test", "test", "test", 1);

		// when
		Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(signUpRequestDto);

		// then
		violations
				.forEach(error -> {
					assertThat(error.getMessage()).isEqualTo("16자 이내의 영문자 및 숫자와 ?,!,., , 특수문자로 입력해주세요.");
				});
	}

	@Test
	void 계정_생성_테스트() {
		String email = "test@gmail.com";
		String nickname = "테스트계정";
		String encodedPassword = "encodedPassword";
		String university = "이화여자대학교";
		Integer studentId = 1989001;
		MemberStatus status = REGISTERED;

		Member member = createMember(email, nickname, encodedPassword, university,studentId);

		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getNickname()).isEqualTo(nickname);
		assertThat(member.getEncodedPassword()).isEqualTo(encodedPassword);
		assertThat(member.getUniversity()).isEqualTo(university);
		assertThat(member.getStudentNo()).isEqualTo(studentId);
		assertThat(member.getStatus()).isEqualTo(status);
	}

	@Test
	void 계정_업데이트_테스트() {
		String email = "test@gmail.com";
		String nickname = "테스트계정";
		String encodedPassword = "encodedPassword";
		String university = "이화여자대학교";
		Integer studentNo = 1989001;
		String newNickname = "새테스트계정";

		Member member = createMember(email, nickname, encodedPassword, university, studentNo);

		member.updateMember(newNickname);

		assertThat(member.getNickname()).isEqualTo(newNickname);

	}

	@Test
	void 계정_삭제_테스트() {
		String email = "test@gmail.com";
		String nickname = "테스트계정";
		String encodedPassword = "encodedPassword";
		String university = "이화여자대학교";
		Integer studentNo = 1989001;
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