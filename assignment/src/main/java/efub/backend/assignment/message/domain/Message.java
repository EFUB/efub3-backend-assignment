package efub.backend.assignment.message.domain;

import efub.backend.assignment.global.entity.BaseTimeEntity;
import efub.backend.assignment.member.domain.Member;

import efub.backend.assignment.messageroom.domain.MessageRoom;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Message extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId; // 카멜식 표기, mysql에서는 post_id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private MessageRoom messageRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member senderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiverId;

    @Column(columnDefinition = "TEXT") // string이 아니라 text임을 명시
    private String content;

    @Builder
    public Message(Long messageId, MessageRoom messageRoom, Member senderId, Member receiverId, String content) {
        this.messageId = messageId;
        this.messageRoom = messageRoom;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

}