package com.efub.community.domain.board.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.efub.community.domain.board.domain.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostListResponseDto {

	private List<SinglePost> posts;
	private Integer count;

	public static PostListResponseDto of(List<Post> postList) {
		return PostListResponseDto.builder()
			.posts(postList.stream().map(SinglePost::of).collect(Collectors.toList()))
			.count(postList.size())
			.build();
	}

	@Getter
	public static class SinglePost {
		
		private final Long postId;
		private final Long boardId;
		private final String boardName;
		private final String writerName;
		private final LocalDateTime createdDate;
		private final LocalDateTime modifiedDate;

		public SinglePost(Post post) {
			this.postId = post.getPostId();
			this.boardId = post.getBoard().getBoardId();
			this.boardName = post.getBoard().getName();
			if (post.isAnonymous()) {
				this.writerName = "익명";
			} else {
				this.writerName = post.getWriter().getNickname();
			}
			this.createdDate = post.getCreatedDate();
			this.modifiedDate = post.getModifiedDate();
		}

		public static SinglePost of(Post post) {
			return new SinglePost(post);
		}
	}
}
