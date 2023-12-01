package com.efub.community.domain.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.board.domain.PostHeart;
import com.efub.community.domain.member.domain.Member;

public interface PostHeartRepository extends JpaRepository<PostHeart, Long> {

	Integer countByPost(Post post);

	List<PostHeart> findByWriter(Member member);

	boolean existsByWriterAndPost(Member member, Post post);

	Optional<PostHeart> findByWriterAndPost(Member member, Post post);

}
