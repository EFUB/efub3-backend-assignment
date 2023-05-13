package com.example.demo.post.repository;

import com.example.demo.member.domain.Member;
import com.example.demo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostIdAndAndWriter_MemberId(Long postId, Long memberId);

    List<Post> findAllByWriter(Member writer);
}
