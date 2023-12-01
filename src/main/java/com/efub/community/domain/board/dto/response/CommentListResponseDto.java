package com.efub.community.domain.board.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.efub.community.domain.board.domain.Comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentListResponseDto {

	@Getter
	@Builder
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Post {

		private Long postId;
		private List<SingleComment> comments;
		private Integer count;

		public static CommentListResponseDto.Post of(Long postId, List<Comment> commentList) {
			return CommentListResponseDto.Post.builder()
				.postId(postId)
				.comments(commentList.stream().map(SingleComment::of).collect(Collectors.toList()))
				.count(commentList.size())
				.build();
		}

		@Getter
		public static class SingleComment {

			private final Long commentId;
			private final String writerName;
			private final String content;
			private final LocalDateTime createDate;
			private final LocalDateTime modifiedDate;

			public SingleComment(Comment comment) {
				this.commentId = comment.getCommentId();
				if (comment.isAnonymous()) {
					this.writerName = "익명" + commentId;
				} else {
					this.writerName = comment.getWriter().getNickname();
				}
				this.content = comment.getContent();
				this.createDate = comment.getCreatedDate();
				this.modifiedDate = comment.getModifiedDate();
			}

			public static SingleComment of(Comment comment) {
				return new SingleComment(comment);
			}
		}
	}
}
