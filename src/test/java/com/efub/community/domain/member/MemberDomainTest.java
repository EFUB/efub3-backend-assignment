package com.efub.community.domain.member;

import static com.efub.community.domain.member.domain.MemberStatus.*;

import org.junit.jupiter.api.BeforeEach;

import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.domain.MemberStatus;

public abstract class MemberDomainTest {

	protected String email = "test@gmail.com";
	protected String nickname = "테스트계정";
	protected String encodedPassword = "encodedPassword";
	protected String university = "이화여자대학교";
	protected Integer studentNo = 1989001;
	protected MemberStatus unregistered = UNREGISTERED;
	protected MemberStatus registered = REGISTERED;
	protected Member member;

	@BeforeEach
	void setUp() {
		member = Member.builder()
			.email(email)
			.nickname(nickname)
			.encodedPassword(encodedPassword)
			.university(university)
			.studentNo(studentNo)
			.build();
	}
}
