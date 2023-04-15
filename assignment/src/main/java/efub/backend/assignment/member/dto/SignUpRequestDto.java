package efub.backend.assignment.member.dto;

import efub.backend.assignment.member.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
{
        "email" : "efub2@gmail.com",
        "password" : "Efub1234!",
        "nickname" : "퍼비",
        "university" : "이화여자대학교",
        "studentId" : "2076216"
        }
*/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.", regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!.?,])[A-Za-z\\d!.?,]{2,16}$",
            message = "16자 이내의 영문자 및 숫자와 ?,!,., , 특수문자로 입력해주세요.")
    private String password;

    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotBlank(message = "학교는 필수입니다.")
    private String university;

    @NotBlank(message = "학번은 필수입니다.")
    private String studentId;

    @Builder
    public SignUpRequestDto(String email, String password, String nickname, String university, String studentId){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.university = university;
        this.studentId = studentId;
    }

    public Member toEntity(){
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .university(this.university)
                .studentId(this.studentId)
                .build();
    }

}