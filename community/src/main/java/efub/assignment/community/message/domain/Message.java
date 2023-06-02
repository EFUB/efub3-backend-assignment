package efub.assignment.community.message.domain;

import efub.assignment.community.global.entity.BaseTimeEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @NotNull(message = "내용은 필수로 입력되어야 합니다.")
    @Column(updatable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "sender_id", updatable = false)
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "message_room_id", updatable = false)
    private MessageRoom messageRoom;

    @Builder
    public Message(String content, Member sender, MessageRoom messageRoom){
        this.content = content;
        this.sender = sender;
        this.messageRoom = messageRoom;
    }
}
