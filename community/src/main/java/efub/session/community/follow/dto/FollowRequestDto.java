package efub.session.community.follow.dto;

import efub.session.community.account.domain.Member;
import efub.session.community.follow.domain.Follow;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 어노테이션 추가
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowRequestDto {
    private Long followingId;

    public FollowRequestDto(Long followingId) {
        this.followingId = followingId;
    }

    public Follow toEntity(Member follower, Member following){
        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }
}
