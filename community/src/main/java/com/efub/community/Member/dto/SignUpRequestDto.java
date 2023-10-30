package com.efub.community.Member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String pw;
    private String nickname;
    private String univ;
    private String studentId;
}
