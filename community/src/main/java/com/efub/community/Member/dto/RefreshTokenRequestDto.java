package com.efub.community.Member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenRequestDto {
    private String refreshToken;
}