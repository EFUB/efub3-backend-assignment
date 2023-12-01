package com.efub.community.domain.board;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.domain.Comment;
import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.member.domain.Member;

@ExtendWith(SoftAssertionsExtension.class)
public abstract class BoardDomainTest {

	@InjectSoftAssertions
	protected SoftAssertions softly;

	protected Member member1;
	protected Member member2;
	protected String description = "펍이들을 위한 게시판입니다.";
	protected String newDescription = "퍼비들을 위한 게시판입니다.";
	protected Board board1;
	protected Board board2;
	protected Post post1;
	protected Post post2;
	protected String postContent = "올바른 게시글의 내용입니다. (5자 초과)";
	protected Comment comment1;
	protected Comment comment2;
	protected String commentContent = "올바른 댓글의 내용입니다. (5자 초과)";

	@BeforeEach
	void setUp() {
		member1 = Member.builder()
			.email("member1@email.com")
			.nickname("member1")
			.encodedPassword("encodedPassword")
			.university("university")
			.studentNo(1)
			.build();
		member2 = Member.builder()
			.email("member2@email.com")
			.nickname("member2")
			.encodedPassword("encodedPassword")
			.university("university")
			.studentNo(2)
			.build();
		board1 = Board.builder()
			.name("퍼비들의 게시판")
			.description(description)
			.owner(member1)
			.build();
		board2 = Board.builder()
			.name("두 번째 게시판")
			.description("두 번째 게시판을 설명하는 공지사항")
			.owner(member2)
			.build();
		post1 = Post.builder()
			.content(postContent)
			.writer(member1)
			.anonymous(true)
			.board(board1)
			.build();
		post2 = Post.builder()
			.content(postContent)
			.writer(member2)
			.anonymous(false)
			.board(board1)
			.build();
		comment1 = Comment.builder()
			.content(commentContent)
			.writer(member1)
			.post(post1)
			.anonymous(true)
			.build();
		comment2 = Comment.builder()
			.content(commentContent)
			.writer(member1)
			.post(post2)
			.anonymous(true)
			.build();
	}

}
