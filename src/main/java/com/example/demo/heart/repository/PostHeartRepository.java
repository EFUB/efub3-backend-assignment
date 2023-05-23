package com.example.demo.heart.repository;

import com.example.demo.heart.domain.PostHeart;
import com.example.demo.member.domain.Member;
import com.example.demo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostHeartRepository extends JpaRepository<PostHeart, Long> {
    Integer countByPost(Post post);
    List<PostHeart> findByWriter(Member account);
    boolean existsByWriterAndPost(Member account, Post post);
    Optional<PostHeart> findByWriterAndPost(Member account, Post post);
}
