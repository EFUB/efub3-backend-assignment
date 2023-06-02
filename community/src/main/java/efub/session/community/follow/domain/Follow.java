package efub.session.community.follow.domain;

import efub.session.community.account.domain.Member;
import efub.session.community.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Follow extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="follow_id", updatable = false)
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩으로 설정
    @JoinColumn(name="follower_id")
    private Member follower;

    @ManyToOne(fetch=FetchType.LAZY) // 지연로딩으로 설정
    @JoinColumn(name="following_id")
    private Member following;

    @Builder
    public Follow(Member follower, Member following) {
        this.follower = follower;
        this.following = following;
    }
}
