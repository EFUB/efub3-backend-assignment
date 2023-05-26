package efub.backend.assignment.post.controller;

import efub.backend.assignment.heart.dto.HeartRequestDto;
import efub.backend.assignment.heart.service.PostHeartService;
import efub.backend.assignment.post.domain.Post;
import efub.backend.assignment.post.dto.PostModifyRequestDto;
import efub.backend.assignment.post.dto.PostRequestDto;
import efub.backend.assignment.post.dto.PostResponseDto;
import efub.backend.assignment.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // @Controller와 @ResponseBody의 기능을 동시에 사용 → 해당 컨트롤러가 리턴하는 객체는 JSON 형태로 전달됨
@RequestMapping("/posts") // 경로 맵핑
@RequiredArgsConstructor // lombok
public class PostController {

    private final PostService postService;
    private final PostHeartService postHeartService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto postAdd(@RequestBody PostRequestDto requestDto){
        Post post = postService.addPost(requestDto);
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

    @DeleteMapping("/{postId}/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String postRemove(@PathVariable Long postId, @RequestParam Long accountId){
        postService.removePost(postId, accountId);
        return "성공적으로 삭제되었습니다.";
    }

    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postModify(@PathVariable Long postId, @RequestBody PostModifyRequestDto requestDto){
        Post post = postService.modifyPost(postId, requestDto);
        return PostResponseDto.from(post);
    }

    @PostMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createPostHeart(@PathVariable final Long postId, @RequestBody final HeartRequestDto requestDto){
        postHeartService.create(postId, requestDto.getMemberId());
        return "좋아요를 눌렀습니다.";
    }

    @DeleteMapping("{postId}/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deletePostHeart(@PathVariable final Long postId, @RequestParam final Long memberId){
        postHeartService.delete(postId,memberId);
        return "좋아요가 취소되었습니다.";
    }

}