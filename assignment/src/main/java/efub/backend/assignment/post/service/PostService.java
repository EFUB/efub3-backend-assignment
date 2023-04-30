package efub.backend.assignment.post.service;

import efub.backend.assignment.board.domain.Board;
import efub.backend.assignment.board.repository.BoardRepository;
import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.member.repository.MemberRepository;
import efub.backend.assignment.post.domain.Post;
import efub.backend.assignment.post.dto.PostModifyRequestDto;
import efub.backend.assignment.post.dto.PostRequestDto;
import efub.backend.assignment.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 레포지토리만 봄
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Post addPost(PostRequestDto requestDto) {
        Member writer = memberRepository.findById(requestDto.getWriterId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));// Post를 넘겨받는 dto가 request 파라미터

        Board board = boardRepository.findById(requestDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다.")); // Board를 넘겨받는 dto가 request 파라미터

        return postRepository.save( // mysql에서는 insert
                Post.builder()
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .writer(writer)
                        .board(board)
                        .build()
        );
    }

    public List<Post> findPostList() {
        return postRepository.findAll();
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }
    @Transactional(readOnly = true)
    public void removePost(Long postId, Long memberId) { // 리턴값 없음
        Post post = postRepository.findByPostIdAndWriter_MemberId(postId, memberId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        // 메소드를 구체화 시켜주는 방법, postId, accountId에 따라서 접근이 잘못된 것에 대해 알려줄 수 있음
        postRepository.delete(post); // 아무것도 조회해주지 않기에 굳이 리턴할 필요 없음
    }

    public Post modifyPost(Long postId, PostModifyRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        post.updatePost(requestDto);
        return post;
    }
}
