package efub.session.community.follow.repository;

import efub.session.community.account.domain.Member;
import efub.session.community.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    // 팔로우 존재 확인
    Boolean existsByFollowerAndFollowing(Member follower, Member following);


    // 팔로우 목록
    List<Follow> findAllByFollower(Member follower);
    List<Follow> findAllByFollowing(Member following);

    // 팔로우 취소
    Follow findByFollowerAndFollowing(Member follower, Member following);
    void deleteByFollowId(Long followId);

}
