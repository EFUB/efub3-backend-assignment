package efub.backend.assignment.heart.service;

import efub.backend.assignment.heart.domain.PostHeart;
import efub.backend.assignment.heart.repository.PostHeartRepository;
import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.member.service.MemberService;
import efub.backend.assignment.post.domain.Post;
import efub.backend.assignment.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j // Simple Logging Facade for Java, 다양한 로깅 프레임 워크에 대한 추상화(인터페이스) 역할을 하는 라이브러리
@Service
@Transactional
@RequiredArgsConstructor
public class PostHeartService {

    private final PostHeartRepository postHeartRepository;
    private final PostService postService;
    private final MemberService memberService;

    public void create(Long postId, Long memberId) {
        // memberId 참조
        Member member = memberService.findMemberById(memberId);

        // postId 참조
        Post post = postService.findPost(postId);

        // 작성자와 게시글이 존재하는지 확인
        // 한 유저가 한 게시물에 대해 한 번만 좋아요를 누를 수 있도록 제한
        if (isExistsByWriterAndPost(member, post)) {
            throw new RuntimeException("이미 좋아요를 누른 게시물입니다.");
        }

        // Builder 패턴 적용
        PostHeart postHeart = PostHeart.builder()
                .post(post)
                .member(member)
                .build();

        // postHeart 객체를 DB에 저장
        postHeartRepository.save(postHeart);
    }

    public void delete(Long postId, Long memberId) {
        // postId 참조
        Post post = postService.findPost(postId);
        // memberId 참조
        Member member = memberService.findMemberById(memberId);

        // 작성자와 게시글이 존재하는지 확인
        PostHeart postHeart = postHeartRepository.findByWriterAndPost(member, post)
                .orElseThrow(() -> new RuntimeException("좋아요가 존재하지 않습니다."));
        // postHeart 객체를 DB에서 삭제
        postHeartRepository.delete(postHeart);
    }

    public boolean isHeart(Long memberId, Post post){
        // memberId 참조
        Member member = memberService.findMemberById(memberId);
        return isExistsByWriterAndPost(member, post);
    }

    @Transactional(readOnly = true)
    public boolean isExistsByWriterAndPost(Member member, Post post) {
        return postHeartRepository.existsByWriterAndPost(member, post);
    }

    @Transactional(readOnly = true)
    public Integer countPostHeart(Post post) {
        Integer count = postHeartRepository.countByPost(post);
        return count;
    }

}