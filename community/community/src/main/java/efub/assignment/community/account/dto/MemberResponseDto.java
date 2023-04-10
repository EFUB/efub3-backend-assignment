package efub.assignment.community.account.dto;

import efub.assignment.community.account.domain.Member;
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
    private String bio;

    public MemberResponseDto(Long memberId, String email, String nickname, String university, String studentId, String bio) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.university = university;
        this.studentId = studentId;
        this.bio = bio;
    }

    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(member.getMemberId(),
            member.getEmail(),
            member.getNickname(),
            member.getUniversity(),
            member.getStudentId(),
            member.getBio());
    }
}
