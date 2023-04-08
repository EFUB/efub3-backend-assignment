package efub.backend.assignment.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import efub.backend.assignment.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
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