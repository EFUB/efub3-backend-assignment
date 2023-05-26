package efub.assignment.community.post.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.BoardRequestDto;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.PostModifyRequestDto;
import efub.assignment.community.post.dto.PostRequestDto;
import efub.assignment.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Transactional
    public Post addPost(PostRequestDto requestDto) {
        Member writer = memberRepository.findById(requestDto.getWriterId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        return postRepository.save(
                Post.builder()
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .writer(writer)
                        .isPrivate(requestDto.getIsPrivate())
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public List<Post> findPostList() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    @Transactional(readOnly = true)
    public List<Post> findPostListByWriter(Long memberId) {
        Member writer = memberService.findMemberById(memberId);
        return postRepository.findAllByWriter(writer);
    }

    public Post modifyPost(Long postId, PostModifyRequestDto requestDto) {
        Post post = postRepository.findByPostIdAndAndWriter_MemberId(postId, requestDto.getWriterId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        post.updatePost(requestDto);
        return post;
    }

    public void removePost(Long postId, Long memberId) {
        Post post = postRepository.findByPostIdAndAndWriter_MemberId(postId, memberId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        postRepository.delete(post);
    }
}
