package com.example.demo.post.domain;

import com.example.demo.board.domain.Board;
import com.example.demo.global.entity.BaseTimeEntity;
import com.example.demo.member.domain.Member;
import com.example.demo.post.dto.PostModifyRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity{

    // 키값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private Long postId;

    // 익명 여부 (true면 익명)
    @Column(nullable = false)
    private Boolean anonymous;

    // 글 내용
    @Column(columnDefinition = "TEXT")
    private String content;

    // 작성자(Member) foreign key
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    // 글이 속한 게시판(Board) foreign key
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Post (Boolean anonymous, String content, Member writer, Board board) {
        this.anonymous = anonymous;
        this.content = content;
        this.writer = writer;
        this.board = board;
    }

    // 게시글 내용 수정
    public void updatePost(String content) {
        this.content = content;
    }


}
