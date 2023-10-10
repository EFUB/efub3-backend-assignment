package com.efub.community.Member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false, length = 60)
    private String pw;

    @Column(nullable = false, length = 32)
    private String nickname;

    @Column(nullable = false)
    private String univ;

    @Column(nullable = false)
    private String studentId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Member(String email, String pw, String nickname, String univ, String studentId) {
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
        this.univ = univ;
        this.studentId = studentId;
        this.status = Status.REGISTERED;
    }
}
