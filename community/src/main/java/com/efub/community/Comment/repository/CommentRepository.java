package com.efub.community.Comment.repository;

import com.efub.community.Comment.domain.Comment;
import com.efub.community.Post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
    boolean existsByPost_PostId(Long postId);
    List<Comment> findByPost_PostId(Long postId);
}
