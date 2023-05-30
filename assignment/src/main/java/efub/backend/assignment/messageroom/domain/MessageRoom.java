package efub.backend.assignment.messageroom.domain;

import efub.backend.assignment.global.entity.BaseTimeEntity;
import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.post.domain.Post;
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
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "receiver_id")
    private Member receiver;

    @Column(name = "first_message")
    private String firstMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false, name = "post_id")
    private Post post;

    @Builder
    public MessageRoom(Member sender, Member receiver, String firstMessage, Post post) {
        this.sender = sender;
        this.receiver = receiver;
        this.firstMessage = firstMessage;
        this.post = post;
    } // 엔티티의 생성자를 만들 때, id 필드는 생략
}