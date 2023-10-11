package com.efub.community.Post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateRequestDto {
        private String title;
        private String content;

        public PostUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
        }
}
