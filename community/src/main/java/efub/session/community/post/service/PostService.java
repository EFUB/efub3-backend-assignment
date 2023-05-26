package efub.session.community.post.service;

import efub.session.community.account.domain.Member;
import efub.session.community.account.dto.PostModifyRequestDto;
import efub.session.community.account.repository.MemberRepository;
import efub.session.community.account.service.MemberService;
import efub.session.community.post.domain.Post;
import efub.session.community.post.dto.PostRequestDto;
import efub.session.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository; // service는 repository만 봄
    private final MemberRepository accountRepository;
    private final MemberService memberService;

    @Transactional
    public Post addPost(PostRequestDto requestDto) {

        Member writer = accountRepository.findById(requestDto.getMemberID())
                        .orElseThrow(()-> new IllegalAccessError("존재하지 않는 계정입니다."));
        return postRepository.save(
                Post.builder()
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .writer(writer)
                        .build() //entity에 있는 builder 불러옴
        );
    }

    public List<Post> findPostList() {
        return postRepository.findAll();
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public void removePost(Long postId, Long memberId) {
        Post post = postRepository.findByPostIdAndWriter_MemberId(postId, memberId)
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        postRepository.delete(post);
    }

    public Post modifyPost(Long postId, PostModifyRequestDto requestDto) {
        Post post = postRepository.findByPostIdAndWriter_MemberId(postId, requestDto.getMemberId())
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        post.updatePost(requestDto);
        return post;
    }


    public List<Post> findPostListByWriter(Long memberId) {
        Member member = memberService.findMemberById(memberId);
        return postRepository.findAllByWriter(member);
    }

}
