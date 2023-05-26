package efub.assignment.community.heart.service;

import efub.assignment.community.heart.domain.PostHeart;
import efub.assignment.community.heart.repository.PostHeartRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostHeartService {

    private final PostHeartRepository postHeartRepository;
    private final PostService postService;
    private final MemberService memberService;

    public void createPostHeart(Long postId, Long memberId) {
        Member member = memberService.findMemberById(memberId);
        Post post = postService.findPost(postId);

        if (isExistsByMemberAndPost(member, post)) {
            throw new RuntimeException("이미 좋아요를 누른 게시물입니다.");
        }

        PostHeart postHeart = PostHeart.builder()
                .post(post)
                .member(member)
                .build();

        postHeartRepository.save(postHeart);
    }

    public void deletePostHeart(Long postId, Long memberId) {

        Post post = postService.findPost(postId);
        Member member = memberService.findMemberById(memberId);

        PostHeart postHeart = postHeartRepository.findByMemberAndPost(member, post)
                .orElseThrow(() -> new RuntimeException("좋아요가 존재하지 않습니다."));

        postHeartRepository.delete(postHeart);
    }

    @Transactional(readOnly = true)
    public Boolean isExistsByMemberAndPost(Member member, Post post) {
        return postHeartRepository.existsByMemberAndPost(member, post);
    }
}
