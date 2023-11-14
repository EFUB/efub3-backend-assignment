package com.efub.community.Member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // test를 위해 추가
@NoArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String pw;
    private String nickname;
    private String univ;
    private String studentId;
}
