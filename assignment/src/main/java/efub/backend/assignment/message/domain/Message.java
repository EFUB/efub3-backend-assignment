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
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private MessageRoom messageRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @Column(columnDefinition = "TEXT") // string이 아니라 text임을 명시
    private String content;

    @Builder
    public Message(MessageRoom messageRoom, Member sender, Member receiver, String content) {
        this.messageRoom = messageRoom;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}