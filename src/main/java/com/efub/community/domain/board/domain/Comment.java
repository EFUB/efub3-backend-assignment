package com.efub.community.domain.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.efub.community.domain.member.domain.Member;
import com.efub.community.global.common.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long commentId;

	@Column(length = 1000)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", updatable = false)
	private Member writer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", updatable = false)
	private Post post;

	private boolean anonymous;

	@Builder
	public Comment(String content, Member writer, Post post, boolean anonymous) {
		verifyContentLength(content);
		this.content = content;
		this.writer = writer;
		this.post = post;
		this.anonymous = anonymous;
	}

	public void updateComment(String content) {
		verifyContentLength(content);
		this.content = content;
	}

	private void verifyContentLength(String content) {
		if (content.length() < 5) {
			throw new IllegalArgumentException();
		}
	}

	public void setPost(Post post) {
		if (this.post != null) {
			throw new IllegalArgumentException();
		}
		this.post = post;
		if (!post.getCommentList().contains(this)) {
			post.getCommentList().add(this);
		}
	}
}
