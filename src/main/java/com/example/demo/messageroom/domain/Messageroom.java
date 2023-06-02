package com.example.demo.messageroom.domain;

import com.example.demo.global.entity.BaseTimeEntity;
import com.example.demo.member.domain.Member;
import com.example.demo.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
MessageRoom
	Long messageId 			쪽지방 아이디
	String firstMessage			첫 쪽지의 내용
	LocalDateTime created		쪽지방이 생성된 시각
	Member sender			첫 쪽지의 보낸 사람
	Member receiver			첫 쪽지의 받는 사람
	Post post				첫 쪽지가 시작된 게시글
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Messageroom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageroom_id", updatable = false)
    private Long messageroomId;

    @Column(nullable = false)
    private String firstMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Messageroom(String firstMessage, Member sender, Member receiver, Post post) {
        this.firstMessage = firstMessage;
        this.sender = sender;
        this.receiver = receiver;
        this.post = post;
    }
}
