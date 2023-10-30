package com.efub.community.Post.repository;

import com.efub.community.Board.domain.Board;
import com.efub.community.Post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByBoard(Board board);
}
