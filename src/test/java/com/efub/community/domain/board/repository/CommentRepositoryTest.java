package com.efub.community.domain.board.repository;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.efub.community.domain.board.BoardDomainTest;
import com.efub.community.domain.board.domain.Comment;
import com.efub.community.domain.member.repository.MemberRepository;

@DataJpaTest
class CommentRepositoryTest extends BoardDomainTest {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Test
	@DisplayName("특정 유저가 작성한 모든 댓글을 조회합니다.")
	void findByWriter() {
		// given
		memberRepository.save(member1);
		memberRepository.save(member2);
		boardRepository.save(board1);
		postRepository.save(post1);
		postRepository.save(post2);
		commentRepository.save(comment1);
		commentRepository.save(comment2);

		// when
		List<Comment> comments = commentRepository.findByWriter(member1);

		// then
		softly.assertThat(comments).hasSize(2);
		softly.assertThat(comments).contains(comment1);
	}

	@Test
	@DisplayName("특정 게시글의 모든 댓글을 조회합니다.")
	void findByPost() {
		// given
		memberRepository.save(member1);
		boardRepository.save(board1);
		postRepository.save(post1);
		commentRepository.save(comment1);

		// when
		List<Comment> comments = commentRepository.findByPost(post1);

		// then
		softly.assertThat(comments).hasSize(1);
		softly.assertThat(comments).contains(comment1);
	}
}
