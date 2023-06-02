package efub.session.community.follow.controller;

import efub.session.community.account.domain.Member;
import efub.session.community.account.service.MemberService;
import efub.session.community.follow.domain.Follow;
import efub.session.community.follow.dto.FollowListResponseDto;
import efub.session.community.follow.dto.FollowRequestDto;
import efub.session.community.follow.dto.FollowStatusResponseDto;
import efub.session.community.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService; // 의존관계: FollowController -> FollowService
    private final MemberService accountService; // 의존관계: FollowController -> AccountService

    // 팔로우 걸기
    @PostMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public FollowStatusResponseDto addFollow(@PathVariable final Long accountId, @RequestBody final FollowRequestDto requestDto){
        Long id = followService.add(accountId, requestDto);
        Boolean isFollow = followService.isFollowing(accountId, requestDto.getFollowingId());
        Member findAccount = accountService.findMemberById(requestDto.getFollowingId());
        return new FollowStatusResponseDto(findAccount, isFollow);
    }

    // 팔로우 목록 조회
    @GetMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public FollowListResponseDto getFollowList(@PathVariable final Long accountId){
        List<Follow> followerList = followService.findAllByFollowingId(accountId); //팔로우 팔로잉 헷갈리지 말 것 !!
        List<Follow> followingList = followService.findAllByFollowerId(accountId);
        return FollowListResponseDto.of(followerList, followingList);
    }

    // 팔로우 여부 조회
    @GetMapping("/{accountId}/search")
    @ResponseStatus(value = HttpStatus.OK)
    public FollowStatusResponseDto searchAccount(@PathVariable final Long accountId,@RequestParam final String email){
        Member searchAccount = accountService.findMemberByEmail(email);
        Boolean isFollow = followService.isFollowing(accountId,searchAccount.getMemberId());
        return new FollowStatusResponseDto(searchAccount, isFollow);
    }

    // 팔로우 취소
    @DeleteMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public FollowStatusResponseDto deleteFollow(@PathVariable final Long accountId, @RequestParam final Long followingId){
        followService.delete(accountId, followingId);
        Member findAccount = accountService.findMemberById(followingId);
        Boolean isFollow = followService.isFollowing(accountId, followingId);
        return new FollowStatusResponseDto(findAccount, isFollow);
    }

}
