package com.efub.community.Post.domain;

import com.efub.community.Board.domain.Board;
import com.efub.community.Member.domain.Member;
import com.efub.community.Member.global.entity.BaseTimeEntity;
import com.efub.community.Post.dto.PostUpdateRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Boolean isAnonymous;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Post(Long postId, String title, String content, Boolean isAnonymous, Member writer, Board board) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.isAnonymous = isAnonymous;
        this.writer = writer;
        this.board = board;
    }

    public void updatePost(PostUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
