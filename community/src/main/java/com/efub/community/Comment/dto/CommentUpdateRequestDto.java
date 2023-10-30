package com.efub.community.Comment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateRequestDto {
    private Long writerId;
    private String content;

    @Builder
    public CommentUpdateRequestDto(Long writerId, String content){
        this.writerId = writerId;
        this.content = content;
    }
}
