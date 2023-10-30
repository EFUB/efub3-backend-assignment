package com.efub.community.Member.dto;

import com.efub.community.Member.domain.Member;
import com.efub.community.Member.domain.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberInfoResponseDto {
    private Long memberId;
    private String email;
    private String nickname;
    private String university;
    private String studentId;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MemberInfoResponseDto(Member member){
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.university = member.getUniv();
        this.studentId = member.getStudentId();
        this.status = member.getStatus();
        this.createdDate = member.getCreatedAt();
        this.modifiedDate = member.getModifiedAt();
    }
}
