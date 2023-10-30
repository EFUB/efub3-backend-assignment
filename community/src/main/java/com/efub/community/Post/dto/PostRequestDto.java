package com.efub.community.Post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // 테스트를 위해 추가
public class PostRequestDto {
    private String title;
    private String content;
    private Boolean anonymous;
    private Long writerId;
    private Long boardId;
}
