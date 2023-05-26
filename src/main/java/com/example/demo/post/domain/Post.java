package com.example.demo.post.domain;

import com.example.demo.board.domain.Board;
import com.example.demo.comment.domain.Comment;
import com.example.demo.global.entity.BaseTimeEntity;
import com.example.demo.heart.domain.PostHeart;
import com.example.demo.member.domain.Member;
import com.example.demo.post.dto.PostModifyRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    // mappedBy : 연관 관계의 주인(Owner)
    // cascade : 엔티티 삭제 시 연관된 엔티티의 처리 방식
    // orphanRemoval : 고아 객체(연관된 부모 엔티티가 없는 자식 엔티티)의 처리 방식
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostHeart> postHeartList = new ArrayList<>();


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
