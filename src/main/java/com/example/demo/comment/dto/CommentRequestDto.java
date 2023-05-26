package com.example.demo.comment.dto;

import com.example.demo.comment.domain.Comment;
import com.example.demo.member.domain.Member;
import com.example.demo.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {

    @NotNull(message = "작성자 ID를 입력해주세요.")
    private Long memberId;

    @NotNull(message = "내용을 입력해주세요.")
    private String content;

    @Builder
    public CommentRequestDto(Long memberId, String content) {
        this.memberId = memberId;
        this.content = content;
    }

    public Comment toEntity(Post post, Member writer) {
        return Comment.builder()
                .post(post)
                .writer(writer)
                .content(this.content)
                .build();
    }

}
