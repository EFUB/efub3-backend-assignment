package com.example.demo.post.service;

import com.example.demo.board.repository.BoardRepository;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.post.domain.Post;
import com.example.demo.post.dto.PostCreateRequestDto;
import com.example.demo.post.dto.PostModifyRequestDto;
import com.example.demo.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Post write(PostCreateRequestDto requestDto) {
        Post post = new Post (
                requestDto.getAnonymous(),
                requestDto.getContent(),
                memberRepository.findById(requestDto.getWriterId())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다.")),
                boardRepository.findById(requestDto.getBoardId())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."))
        );
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public Long update(Long postId, PostModifyRequestDto requestDto) {
        Post post = findPostById(postId);
        post.updatePost(requestDto.getContent());
        return post.getPostId();
    }

    @Transactional
    public void deletePost(Long postId, Long memberId) {
        Post post = postRepository.findByPostIdAndAndWriter_MemberId(postId, memberId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        postRepository.delete(post);
    }
}
