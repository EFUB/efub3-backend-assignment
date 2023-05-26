package com.example.demo.heart.service;

import com.example.demo.heart.domain.PostHeart;
import com.example.demo.heart.repository.PostHeartRepository;
import com.example.demo.member.domain.Member;
import com.example.demo.member.service.MemberService;
import com.example.demo.post.domain.Post;
import com.example.demo.post.service.PostService;
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

    public void create(Long postId, Long memberId) {
        Member member = memberService.findMemberById(memberId);
        Post post = postService.findPostById(postId);

        if (isExistsByWriterAndPost(member, post)) throw new RuntimeException("이미 좋아요를 누른 게시물입니다.");

        PostHeart postHeart = PostHeart.builder()
                .post(post)
                .writer(member)
                .build();

        postHeartRepository.save(postHeart);
    }

    public void delete(Long postId,Long memberId) {
        Post post = postService.findPostById(postId);
        Member member = memberService.findMemberById(memberId);
        PostHeart postLike = postHeartRepository.findByWriterAndPost(member, post)
                .orElseThrow(() -> new RuntimeException("좋아요가 존재하지 않습니다."));
        postHeartRepository.delete(postLike);
    }

    public boolean isHeart(Long memberId, Post post) {
        Member member = memberService.findMemberById(memberId);
        return isExistsByWriterAndPost(member, post);
    }

    @Transactional(readOnly = true)
    private boolean isExistsByWriterAndPost(Member member, Post post) {
        return postHeartRepository.existsByWriterAndPost(member, post);
    }

    @Transactional(readOnly = true)
    public Integer countPostHeart(Post post) {
        return postHeartRepository.countByPost(post);
    }
}
