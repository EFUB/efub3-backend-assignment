package com.efub.community.domain.member.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.efub.community.domain.member.MemberDomainTest;
import com.efub.community.domain.member.domain.Member;

// @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
class MemberRepositoryTest extends MemberDomainTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@DisplayName("유저를 저장합니다.")
	void saveMember() {
		Member savedMember = memberRepository.save(member);

		assertThat(savedMember.getEmail()).isEqualTo(email);
		assertThat(savedMember.getEncodedPassword()).isEqualTo(encodedPassword);
		assertThat(savedMember.getUniversity()).isEqualTo(university);
		assertThat(savedMember.getStudentNo()).isEqualTo(studentNo);
	}

	@Test
	@DisplayName("유저를 유효한 아이디로 검색합니다.")
	void findByMemberId() {
		memberRepository.save(member);

		Optional<Member> memberOptional = memberRepository.findByMemberId(member.getMemberId());

		assertThat(memberOptional).isNotEmpty();
	}

	@Test
	@DisplayName("유저를 유효하지 않은 아이디로 검색합니다.")
	void findByMemberId_InvalidId() {
		memberRepository.save(member);

		Optional<Member> memberOptional = memberRepository.findByMemberId(5L);

		assertThat(memberOptional).isEmpty();
	}

	@Test
	@DisplayName("닉네임으로 유저의 존재여부를 검색합니다.")
	void testExistsByNickname() {
		Member savedMember = memberRepository.save(member);

		assertThat(memberRepository.existsByNickname(savedMember.getNickname())).isTrue();
		assertThat(memberRepository.existsByNickname("존재하지 않는 닉네임")).isFalse();
	}

}
