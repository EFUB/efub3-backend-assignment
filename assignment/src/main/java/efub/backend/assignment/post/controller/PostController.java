package efub.backend.assignment.post.controller;

import efub.backend.assignment.post.domain.Post;
import efub.backend.assignment.post.dto.PostModifyRequestDto;
import efub.backend.assignment.post.dto.PostRequestDto;
import efub.backend.assignment.post.dto.PostResponseDto;
import efub.backend.assignment.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController // @Controller와 @ResponseBody의 기능을 동시에 사용 → 해당 컨트롤러가 리턴하는 객체는 JSON 형태로 전달됨
@RequestMapping("/posts") // 경로 맵핑
@RequiredArgsConstructor // lombok
public class PostController {

    private final PostService postService;

    @PostMapping // HTTP 전송 방식에 따라, 해당 메소드의 방식을 지정, 여기서는 Post
    @ResponseStatus(value = HttpStatus.CREATED) // 반환하는 객체의 HTTP 응답의 상태 코드: created
    public PostResponseDto postAdd(@RequestBody PostRequestDto requestDto){
        // @RequestBody: HTTP 요청이 JSON임을 명시
        Post post = postService.addPost(requestDto);
        return new PostResponseDto(post);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostResponseDto> postListFind() {
        List<Post> postList = postService.findPostList();
        List<PostResponseDto> responseDtoList = new ArrayList<>();

        /* for (Post post : postList) {
            responseDtoList.add(new PostResponseDto(post));
        } // stream으로 한줄로 바꾸는것 시도해보기! */

        responseDtoList = postList.stream()
                .map(PostResponseDto::new)// map메소드는 Post 객체를 PostResponseDto 객체로 변환하기 위해 클래스의 생성자 사용
                .collect(Collectors.toList()); // collect메소드는 List<PostResponseDto>로 변환

        return responseDtoList;
    }

    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postFind(@PathVariable Long postId) {
        // @PathVariable: URL 경로의 일부를 변수로 삼아서 처리하기 위해서 사용
        Post post = postService.findPost(postId);
        return new PostResponseDto(post);
    }

    @DeleteMapping("/{postId}/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String postRemove(@PathVariable Long postId, @RequestParam Long memberId){
        // @RequestParam: Request에 있는 특정한 이름의 데이터를 파라미터로 받아서 처리하는 경우에 사용
        postService.removePost(postId, memberId);
        return "성공적으로 삭제되었습니다.";
    }

    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postModify(@PathVariable Long postId, @RequestBody PostModifyRequestDto requestDto){
        // @PathVariable: URL 경로의 일부를 변수로 삼아서 처리하기 위해서 사용
        // @RequestBody: HTTP 요청이 JSON임을 명시
        Post post = postService.modifyPost(postId, requestDto);
        return new PostResponseDto(post);
    }

}