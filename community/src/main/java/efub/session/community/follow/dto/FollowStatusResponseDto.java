package efub.session.community.follow.dto;

import efub.session.community.account.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowStatusResponseDto {
    private Long accountId;
    private String nickname;
    private String email;
    private String status;

    @Builder
    public FollowStatusResponseDto(Member account, Boolean isFollow) {
        this.accountId = account.getMemberId();
        this.nickname = account.getNickname();
        this.email = account.getEmail();
        if(!isFollow){
            this.status = "UNFOLLOW";
        }else{
            this.status = "FOLLOW";
        }
    }
}