package com.efub.community.domain.member.auth.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDto {

	@NotNull(message = "이메일은 필수로 입력해야 합니다. ")
	private String email;

	// 해당 값이 null이 아니고, 공백(""과 " " 모두 포함)이 아닌지 검증
	@NotBlank(message = "비밀번호는 필수입니다.")
	private String password;

	@Builder
	public LoginRequestDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
