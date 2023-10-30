package com.efub.community.Post.dto;

import com.efub.community.Board.dto.BoardResponseDto;
import com.efub.community.Member.dto.MemberInfoResponseDto;
import com.efub.community.Post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private Boolean anonymous;
    private MemberInfoResponseDto member;
    private BoardResponseDto board;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto (Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.anonymous = post.getIsAnonymous();
        this.member = new MemberInfoResponseDto(post.getWriter()); // MemberInfoResponseDto를 생성
        this.board = new BoardResponseDto(post.getBoard()); // BoardResponseDto를 생성
        this.createdDate = post.getCreatedAt();
        this.modifiedDate = post.getModifiedAt();
    }
}
