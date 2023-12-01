package com.efub.community.domain.board.repository;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.efub.community.domain.board.BoardDomainTest;
import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.member.repository.MemberRepository;

@DataJpaTest
class PostRepositoryTest extends BoardDomainTest {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private PostRepository postRepository;

	private void saveEntitiesForPost1() {
		memberRepository.save(member1);
		boardRepository.save(board1);
		postRepository.save(post1);
	}

	private void saveEntitiesForPost2() {
		memberRepository.save(member2);
		postRepository.save(post2);
	}

	@Test
	@DisplayName("특정 유저가 작성한 모든 게시글을 조회합니다.")
	void findByWriter() {
		// given
		saveEntitiesForPost1();

		// when
		List<Post> postList = postRepository.findAllByWriter(member1);

		// then
		softly.assertThat(postList).hasSize(1);
		softly.assertThat(postList.get(0).getContent()).isEqualTo(postContent);
	}

	@Test
	@DisplayName("전체 게시글을 아이디를 기준으로 내림차순 조회합니다.")
	void findAllByOrderByPostIdDesc() {
		// given
		saveEntitiesForPost1();
		saveEntitiesForPost2();

		// when
		List<Post> postList = postRepository.findAllByOrderByPostIdDesc();

		// then
		softly.assertThat(postList).hasSize(2);
		softly.assertThat(postList.get(0).getPostId()).isGreaterThan(postList.get(1).getPostId());
	}

	@Test
	@DisplayName("특정 게시판의 모든 게시글을 조회합니다.")
	void findAllByBoard() {
		// given
		saveEntitiesForPost1();
		saveEntitiesForPost2();

		// when
		List<Post> postList = postRepository.findAllByBoard(board1);

		// then
		softly.assertThat(postList).hasSize(2);
		softly.assertThat(postList).contains(post1);
	}
}
