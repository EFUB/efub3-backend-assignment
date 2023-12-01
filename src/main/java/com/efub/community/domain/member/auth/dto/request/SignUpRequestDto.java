package com.efub.community.domain.member.auth.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.efub.community.domain.member.domain.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {

	@NotBlank(message = "이메일은 필수입니다.")
	private String email;

	@NotBlank(message = "비밀번호는 필수입니다.")
	private String password;

	@NotBlank(message = "닉네임은 필수입니다. ")
	private String nickname;

	@NotBlank(message = "학교 이름은 필수입니다. ")
	private String university;

	@NotNull(message = "학번은 필수입니다.")
	private Integer studentNo;

	@Builder
	public SignUpRequestDto(String email, String password, String nickname, String university, Integer studentNo) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.university = university;
		this.studentNo = studentNo;
	}

	public Member toEntity(String encodedPassword) {
		return Member.builder()
			.email(this.email)
			.encodedPassword(encodedPassword)
			.nickname(this.nickname)
			.university(this.university)
			.studentNo(this.studentNo)
			.build();
	}
}
