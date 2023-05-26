package com.example.demo.comment.domain;


import com.example.demo.heart.domain.CommentHeart;
import com.example.demo.member.domain.Member;
import com.example.demo.post.domain.Post;
import com.example.demo.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id // 이 필드가 이 엔티티의 프라이머리 키이다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 테이블에 데이터를 넣을 때, 이 필드는 auto_increment로 생성된다
    private Long commentId;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)  // Comment가 Many, Post가 One
    @JoinColumn(name = "post_id", nullable = false, updatable = false)   // Post 측의 post_id 라는 컬럼과 join 되어 생성될 것
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)  // Comment가 Many, Account(작성자)가 One
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private Member writer;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CommentHeart> commentHeartList = new ArrayList<>();


    @Builder    // 이게 없다면 일일히 코드를 쳐서 Builder 클래스를 만들어야 함. 요즘은 아무도 그렇게 안 하고 @Builder 를 씀.
    public Comment(String content, Post post, Member writer) {
        this.content = content;
        this.post = post;
        this.writer = writer;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
