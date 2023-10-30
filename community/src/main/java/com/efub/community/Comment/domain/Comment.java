package com.efub.community.Comment.domain;

import com.efub.community.Comment.dto.CommentUpdateRequestDto;
import com.efub.community.Member.domain.Member;
import com.efub.community.Member.global.entity.BaseTimeEntity;
import com.efub.community.Post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Builder
    public Comment(String content, Member writer, Post post){
        this.content = content;
        this.writer = writer;
        this.post = post;
    }

    public void updateComment(CommentUpdateRequestDto requestDto){
        this.content = requestDto.getContent();
    }
}
