package com.example.demo.member.dto;

import com.example.demo.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
    private Long memberId;

    private String email;

    private String nickname;

    private String university;

    private String studentId;

    public MemberResponseDto (Long memberId, String email, String nickname, String university, String studentId) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.university = university;
        this. studentId = studentId;
    }

    // 외부에서 받아온 Member 객체를 Dto 객체로 만들어 리턴하는 함수
    public static MemberResponseDto from (Member member) {
        return new MemberResponseDto(member.getMemberId(),
                member.getEmail(),
                member.getNickname(),
                member.getUniversity(),
                member.getStudentId());
    }

}