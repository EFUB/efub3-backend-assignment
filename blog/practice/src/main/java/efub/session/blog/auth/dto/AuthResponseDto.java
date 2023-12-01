package efub.session.blog.auth.dto;


import efub.session.blog.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthResponseDto {
    private String nickname;
    private String email;
    private String accessToken;
    private String refreshToken;

    @Builder
    public AuthResponseDto(Member member, String accessToken, String refreshToken) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
