package com.efub.community.domain.member.auth.dto.response;

import com.efub.community.domain.member.domain.Member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpResponseDto {

	private Long accountId;
	private String email;
	private String nickname;
	private String university;
	private Integer studentNo;

	public SignUpResponseDto(Member member) {
		this.accountId = member.getMemberId();
		this.email = member.getEmail();
		this.nickname = member.getNickname();
		this.university = member.getUniversity();
		this.studentNo = member.getStudentNo();
	}
}
