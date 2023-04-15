package efub.backend.assignment.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import efub.backend.assignment.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
/*
{
	"memberId" : 1,
    "email" : "efub2@gmail.com",
    "nickname" : "퍼비",
    "university" : "이화여자대학교",
    "studentId" : "2076216"
}
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
    private String email;
    private String nickname;
    private String university;
    private String studentId;
    // private String bio;
    public MemberResponseDto(String email, String nickname, String university, String studentId){
        this.email = email;
        this.nickname = nickname;
        this.university = university;
        this.studentId = studentId;
    }

    public static MemberResponseDto from(Member member){
        return new MemberResponseDto(
                member.getEmail(),
                member.getNickname(),
                member.getUniversity(),
                member.getStudentId());
    }
}