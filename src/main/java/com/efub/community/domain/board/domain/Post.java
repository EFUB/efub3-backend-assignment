package com.efub.community.domain.board.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.efub.community.domain.member.domain.Member;
import com.efub.community.global.common.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Comment> commentList = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<PostHeart> postHeartList = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long postId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn(name = "board_id", insertable = false, updatable = false)
	private Board board;

	private boolean anonymous;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member writer;

	@Builder
	public Post(String content, Member writer, Boolean anonymous, Board board) {
		verifyContentLength(content);
		this.content = content;
		this.writer = writer;
		this.anonymous = anonymous;
		this.board = board;
	}

	public void updatePost(String content) {
		verifyContentLength(content);
		this.content = content;
	}

	private void verifyContentLength(String content) {
		if (content.length() < 5) {
			throw new IllegalArgumentException();
		}
	}

}

