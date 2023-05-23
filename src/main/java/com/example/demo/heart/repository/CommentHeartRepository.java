package com.example.demo.heart.repository;

import com.example.demo.comment.domain.Comment;
import com.example.demo.heart.domain.CommentHeart;
import com.example.demo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {
    Integer countByComment(Comment comment);
    List<CommentHeart> findByWriter(Member account);
    boolean existsByWriterAndComment(Member account, Comment comment);
    Optional<CommentHeart> findByWriterAndComment(Member account, Comment comment);
}
