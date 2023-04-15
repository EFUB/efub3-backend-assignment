package com.example.demo.post.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 게시글의 내용 변경 요청을 보낼 때 사용하는 DTO
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostModifyRequestDto {

    private String content;

    @Builder
    public PostModifyRequestDto (String content) {
        this.content = content;
    }
}
