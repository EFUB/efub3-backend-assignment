package com.efub.community.domain.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.efub.community.domain.board.domain.Board;
import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.member.domain.Member;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findAllByWriter(Member member);

	List<Post> findAllByOrderByPostIdDesc();

	List<Post> findAllByBoard(Board board);

	@Query("SELECT p FROM Post p WHERE (SELECT COUNT(l) FROM p.postHeartList l) >= :heartCount ORDER BY p.createdDate DESC")
	List<Post> findPopularPosts(@Param("heartCount") Integer heartCount);
}
