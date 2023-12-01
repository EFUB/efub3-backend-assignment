package com.efub.community.domain.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efub.community.domain.board.domain.Comment;
import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.member.domain.Member;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByWriter(Member member);

	List<Comment> findByPost(Post post);
}
