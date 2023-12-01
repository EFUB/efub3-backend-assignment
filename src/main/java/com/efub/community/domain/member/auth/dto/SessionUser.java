package com.efub.community.domain.member.auth.dto;

import com.efub.community.domain.member.domain.Member;

import lombok.Getter;

@Getter
public class SessionUser {

	private final String email;

	public SessionUser(Member member) {
		this.email = member.getEmail();
	}
}
