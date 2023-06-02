package efub.session.blog.message.domain;

import efub.session.blog.global.BaseTimeEntity;
import efub.session.blog.member.domain.Member;
import efub.session.blog.messageRoom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", updatable = false)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_room_id")
    private MessageRoom messageRoomId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;


    @Builder
    public Message(MessageRoom messageRoomId, String content, Member sender, Member receiver) {
        this.messageRoomId = messageRoomId;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }
}
