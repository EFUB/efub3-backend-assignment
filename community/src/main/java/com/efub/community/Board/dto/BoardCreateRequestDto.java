package com.efub.community.Board.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // test를 위해 추가
@NoArgsConstructor
public class BoardCreateRequestDto {
    private String boardName;
    private String boardDesc;
    private String notice;
    private Long ownerId;
}
