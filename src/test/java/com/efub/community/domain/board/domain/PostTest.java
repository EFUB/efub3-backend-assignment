package com.efub.community.domain.board.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.efub.community.domain.board.BoardDomainTest;

class PostTest extends BoardDomainTest {

	@Test
	@DisplayName("포스트를 업데이트합니다.")
	void update() {
		// given
		String newContent = "새로 업데이트되는 게시글의 내용입니다.";
		// when
		assertThat(post1.getContent()).isEqualTo(postContent);
		post1.updatePost(newContent);
		// then
		assertThat(post1.getContent()).isEqualTo(newContent);
	}

	@Test
	@DisplayName("포스트 생성 시 게시글의 내용이 5자 미만이면, 예외가 발생합니다.")
	void createShortPost() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Post.builder()
				.content("게시글")
				.writer(member1)
				.anonymous(true)
				.board(board1)
				.build());
	}

	@Test
	@DisplayName("포스트 업데이트 시 게시글의 내용이 5자 미만이면, 예외가 발생합니다.")
	void updateShortPost() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> post1.updatePost("게시글"));
	}

}
