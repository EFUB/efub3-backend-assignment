package com.efub.community.Comment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CommentUpdateRequestDto {
    private Long writerId;
    private String content;

    @Builder
    public CommentUpdateRequestDto(Long writerId, String content){
        this.writerId = writerId;
        this.content = content;
    }
}
