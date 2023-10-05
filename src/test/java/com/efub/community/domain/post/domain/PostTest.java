package com.efub.community.domain.post.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.member.domain.Member;

public class PostTest {

	@Test
	void postCreateTest(){
		String content="content";
		Member writer= new Member("test@gmail.com","encodedPassword","nickname",1,"ewha");
		Board board=new Board("오늘 날씨","오늘 날씨 완전 시원하당", writer);
		boolean anonymous=false;

		Post post = Post.builder()
			.content(content)
			.writer(writer)
			.anonymous(anonymous)
			.board(board)
			.build();

		assertThat(post.getContent()).isEqualTo(content);
		assertThat(post.getWriter()).isEqualTo(writer);
		assertThat(post.getBoard()).isEqualTo(board);
		assertThat(post.isAnonymous()).isEqualTo(anonymous);
	}
}
