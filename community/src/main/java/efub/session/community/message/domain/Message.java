package efub.session.community.message.domain;

import efub.session.community.global.entity.BaseTimeEntity;
import efub.session.community.account.domain.Member;
import efub.session.community.messageroom.domain.MessageRoom;
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
    @Column(name = "message_id")
    private java.lang.Long messageId;


    // 여러개의 쪽지로 하나의 쪽지방에서 대화
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "messageRoom")
    private MessageRoom messageRoom;


    // 여러 개의 쪽지 보내기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    // 쪽지 내용
    @Column
    private String content;

    @Builder
    public Message(MessageRoom messageRoom, Member sender, String content){
        this.messageRoom=messageRoom;
        this.sender=sender;
        this.content=content;
    }

}
