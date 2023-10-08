package com.efub.community.domain.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.efub.community.domain.member.domain.Member;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE, connection = EmbeddedDatabaseConnection.H2)
public class MemberRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private MemberRepository memberRepository;

	@Test
	void saveMemberTest(){
		String email = "test@naver.com";
		String nickname = "nickname";
		String encodedPassword = "encodedPassword";
		String university = "ewha";
		Integer studentNo = 1976393;

		Member member = Member.builder()
			.email(email)
			.nickname(nickname)
			.encodedPassword(encodedPassword)
			.university(university)
			.studentNo(studentNo)
			.build();

		testEntityManager.persist(member);
		Optional<Member> memberOptional= memberRepository.findByMemberId(member.getMemberId());

		assertThat(memberOptional).isNotEmpty();
	}
}
