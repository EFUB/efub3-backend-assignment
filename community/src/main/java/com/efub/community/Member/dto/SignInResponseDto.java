package com.efub.community.Member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInResponseDto {
    private Long memberId;
    private String email;
    private String accessToken;
    private String refreshToken;

    @Builder
    public SignInResponseDto(Long memberId, String email, String pw, String accessToken, String refreshToken) {
        this.memberId = memberId;
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
