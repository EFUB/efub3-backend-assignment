package efub.session.community.follow.service;

import efub.session.community.account.domain.Member;
import efub.session.community.account.service.MemberService;
import efub.session.community.follow.domain.Follow;
import efub.session.community.follow.dto.FollowRequestDto;
import efub.session.community.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor // final이 붙은 필드의 생성자를 자동으로 생성
public class FollowService {
    // 의존관계 : FollowService->FollowRepository
    private final FollowRepository followRepository;
    // 의존관계 : FollowService->MemberService
    public final MemberService memberService;

    // 팔로우 추가
    public Long add(Long memberId, FollowRequestDto followRequestDto){
        Member follower = memberService.findMemberById(memberId);
        Member following = memberService.findMemberById(followRequestDto.getFollowingId());
        Follow follow = followRepository.save(followRequestDto.toEntity(follower, following));
        return follow.getFollowId();
    }

    // 팔로우 여부
    @Transactional(readOnly = true)
    public boolean isFollowing(Long followerId, Long followingId){
        Member follower = memberService.findMemberById(followerId);
        Member following = memberService.findMemberById(followingId);
        return followRepository.existsByFollowerAndFollowing(follower, following);
    }

    // 팔로워 목록
    @Transactional(readOnly = true)
    public List<Follow> findAllByFollowerId(Long accountId){
        Member findMember = memberService.findMemberById(accountId);
        return followRepository.findAllByFollower(findMember);
    }

    // 팔로잉 목록
    @Transactional(readOnly = true)
    public List<Follow> findAllByFollowingId(Long memberId){
        Member findMember = memberService.findMemberById(memberId);
        return followRepository.findAllByFollowing(findMember);
    }

    // 팔로우 취소
    @Transactional(readOnly = true)
    public Follow findByFollowerIdAndFollowingId(Long followerId, Long followingId){
        Member follower = memberService.findMemberById(followerId);
        Member following = memberService.findMemberById(followingId);
        return followRepository.findByFollowerAndFollowing(follower, following);
    }

    public void delete(Long memberId, Long followingId) {
        Follow findFollow = findByFollowerIdAndFollowingId(memberId, followingId);
        followRepository.deleteByFollowId(findFollow.getFollowId());
    }

}
