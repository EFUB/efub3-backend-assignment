package com.efub.community.domain.comment.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.domain.Comment;
import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.member.domain.Member;

public class CommentTest {

	@Test
	void commentCreateTest(){
		String content="content";
		Member writer= new Member("test@gmail.com","encodedPassword","nickname",1,"ewha");
		Board board=new Board("오늘 날씨","오늘 날씨 완전 시원하당", writer);
		boolean anonymous=false;
		Post post= new Post(content, writer, anonymous, board);

		Comment comment = createComment(content, writer, post, anonymous);

		assertThat(comment.getContent()).isEqualTo(content);
		assertThat(comment.getWriter()).isEqualTo(writer);
		assertThat(comment.getPost()).isEqualTo(post);
		assertThat(comment.getPost().isAnonymous()).isEqualTo(anonymous);


	}
	private Comment createComment(String content, Member writer, Post post, boolean anonymous){
		Comment comment = Comment.builder()
			.content(content)
			.writer(writer)
			.post(post)
			.anonymous(anonymous)
			.build();
		return comment;
	}
}
