package efub.assignment.community.post.controller;

import efub.assignment.community.heart.dto.HeartRequestDto;
import efub.assignment.community.heart.service.PostHeartService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.PostModifyRequestDto;
import efub.assignment.community.post.dto.PostRequestDto;
import efub.assignment.community.post.dto.PostResponseDto;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostHeartService postHeartService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto postAdd(@RequestBody PostRequestDto postRequestDto) {
        Post post = postService.addPost(postRequestDto);
        return PostResponseDto.from(post);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostResponseDto> postListFind() {
        List<Post> postList = postService.findPostList();
        return postList.stream().map(PostResponseDto::from).collect(Collectors.toList());
    }

    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postFind(@PathVariable Long postId) {
        Post post = postService.findPost(postId);
        return PostResponseDto.from(post);
    }

    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postModify(@PathVariable Long postId, @RequestBody PostModifyRequestDto requestDto) {
        Post post = postService.modifyPost(postId, requestDto);
        return PostResponseDto.from(post);
    }

    @DeleteMapping("/{postId}/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String postRemove(@PathVariable Long postId, @RequestParam Long memberId) {
        postService.removePost(postId, memberId);
        return "성공적으로 삭제되었습니다.";
    }

    // 게시글 좋아요
    @PostMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createPostHeart(@PathVariable final Long postId, @RequestBody final HeartRequestDto requestDto) {
        postHeartService.create(postId, requestDto.getMemberId());
        return "좋아요를 눌렀습니다.";
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deletePostHeart(@PathVariable final Long postId, @RequestParam final Long memberId) {
        postHeartService.delete(postId, memberId);
        return "좋아요가 취소되었습니다.";
    }
}
