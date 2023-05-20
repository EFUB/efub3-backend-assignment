package efub.session.blog.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequestDto {
    private String bio;
    @NotBlank(message = "닉네임은 필수값입니다.")
    private String nickname;

    @Builder
    public MemberUpdateRequestDto(String bio,String nickname){
        this.bio=bio;
        this.nickname=nickname;
    }

}
