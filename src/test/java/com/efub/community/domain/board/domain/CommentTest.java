package com.efub.community.domain.board.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.efub.community.domain.board.BoardDomainTest;

class CommentTest extends BoardDomainTest {

	@Test
	@DisplayName("댓글을 업데이트합니다.")
	void update() {
		// given
		String newContent = "새로 업데이트되는 댓글의 내용입니다.";
		// when
		assertThat(comment1.getContent()).isEqualTo(commentContent);
		comment1.updateComment(newContent);
		// then
		assertThat(comment1.getContent()).isEqualTo(newContent);
	}

	@Test
	@DisplayName("댓글 생성 시 댓글의 내용이 5자 미만이면, 예외가 발생합니다.")
	void createShortPost() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Comment.builder()
				.content("댓글")
				.writer(member1)
				.post(post1)
				.anonymous(true)
				.build());
	}

	@Test
	@DisplayName("댓글에 게시글을 설정할 때 게시글이 null이 아니면, 예외가 발생합니다.")
	void updateShortPost() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> comment1.setPost(post2));
	}

}
