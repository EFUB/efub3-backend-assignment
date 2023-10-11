package com.efub.community.Board.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCreateRequestDto {
    private String boardName;
    private String boardDesc;
    private String notice;
    private Long ownerId;
}
