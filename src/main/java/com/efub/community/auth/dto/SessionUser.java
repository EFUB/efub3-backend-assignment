package com.efub.community.auth.dto;

import com.efub.community.domain.member.domain.Member;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private String nickname;
    private String email;

    public SessionUser(Member member) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
    }
}
