package com.efub.community.Member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // test를 위해 추가
@NoArgsConstructor
public class SignInRequestDto {
    private String email;
    private String pw;
}
