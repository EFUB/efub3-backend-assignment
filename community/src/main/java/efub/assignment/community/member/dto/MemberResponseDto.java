package efub.assignment.community.member.dto;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.domain.MemberStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    private Long memberId;
    private String email;
    private String nickname;
    private String university;
    private String studentId;
    private MemberStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MemberResponseDto(Member member){
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.university = member.getUniversity();
        this.studentId = member.getStudentId();
        this.status = member.getStatus();
        this.createdDate = member.getCreatedDate();
        this.modifiedDate = member.getModifiedDate();
    }
}
