package com.example.demo.messageroom.dto;

import com.example.demo.member.domain.Member;
import com.example.demo.messageroom.domain.Messageroom;
import com.example.demo.post.domain.Post;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageroomCreateRequestDto {
    @NotNull(message = "보낸이의 ID를 입력해주세요.")
    private Long senderId;
    @NotNull(message = "받는이의 ID를 입력해주세요.")
    private Long receiverId;
    @NotNull(message = "첫 쪽지의 내용을 입력해주세요.")
    private String firstMessage;
    @NotNull(message = "첫 쪽지가 시작된 게시글의 아이디를 입력해주세요.")
    private Long postId;

    @Builder
    public MessageroomCreateRequestDto (Long senderId, Long receiverId, String firstMessage, Long postId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.firstMessage = firstMessage;
        this.postId = postId;
    }

    public Messageroom toEntity(Member sender, Member receiver, Post post) {
        return Messageroom.builder()
                .firstMessage(this.firstMessage)
                .sender(sender)
                .receiver(receiver)
                .post(post)
                .build();
    }


}
