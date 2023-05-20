package efub.session.blog.member.controller;

import efub.session.blog.member.service.MemberService;
import efub.session.blog.post.domain.Post;
import efub.session.blog.post.dto.PostListResponseDto;
import efub.session.blog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/posts")
public class MemberPostController {
    private final PostService postService;
    private final MemberService memberService;

    //특정 유저의 게시글 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostListResponseDto readMemberPosts(@PathVariable Long memberId){
        List<Post> postList = postService.findPostListByWriter(memberId);
        return PostListResponseDto.of(postList);
    }
}
