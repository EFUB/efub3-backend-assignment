package com.efub.community.Post.service;

import com.efub.community.Board.domain.Board;
import com.efub.community.Board.repository.BoardRepository;
import com.efub.community.Member.domain.Member;
import com.efub.community.Member.repository.MemberRepository;
import com.efub.community.Post.domain.Post;
import com.efub.community.Post.dto.PostRequestDto;
import com.efub.community.Post.dto.PostUpdateRequestDto;
import com.efub.community.Post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Post createPost(PostRequestDto requestDto) {
        Member writer = memberRepository.findById(requestDto.getWriterId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 계정입니다."));

        Board board = boardRepository.findById(requestDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."));

        return postRepository.save(
                Post.builder()
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .isAnonymous(requestDto.getAnonymous())
                        .writer(writer)
                        .board(board)
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
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    @Transactional(readOnly = true)
    public List<Post> findPostsInBoard(Long boardId){
        List<Post> all = findPostList();
        List<Post> selected = new ArrayList<>();

        for(Post p:all) {
            if(p.getBoard().getBoardId().equals(boardId))
                selected.add(p);
        }

        return selected;
    }

    public Post updatePost(Long postId, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        post.updatePost(requestDto);
        return post;
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));

        postRepository.delete(post);
    }

}