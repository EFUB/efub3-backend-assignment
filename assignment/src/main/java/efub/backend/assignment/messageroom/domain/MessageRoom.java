package efub.backend.assignment.messageroom.domain;

import efub.backend.assignment.global.entity.BaseTimeEntity;
import efub.backend.assignment.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MessageRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "sender_id")
    private Member senderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "receiver_id")
    private Member receiverId;

    @Column(nullable = false, name = "message_content")
    private String messageContent;

    @Column(nullable = false, name = "message_id")
    private Long messageId;

    @Builder
    public MessageRoom(Long roomId, Member senderId, Member receiverId, String messageContent, Long messageId) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageContent = messageContent;
        this.messageId = messageId;
    }
}