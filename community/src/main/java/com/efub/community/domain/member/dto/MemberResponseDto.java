package com.efub.community.domain.member.dto;

import com.efub.community.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
	private String email;
	private String nickname;
	private String university;
	private Integer studentNo;

	public MemberResponseDto(Member member) {
		this.email = member.getEmail();
		this.nickname = member.getNickname();
		this.university = member.getUniversity();
		this.studentNo = member.getStudentNo();
	}
}