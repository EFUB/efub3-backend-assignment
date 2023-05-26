package com.example.demo.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoRequestDto {
    @NotNull(message = "작성자는 필수")
    private Long memberId;

    public MemberInfoRequestDto(Long memberId) {
        this.memberId = memberId;
    }
}
