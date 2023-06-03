package efub.backend.assignment.messageroom.domain;

import efub.backend.assignment.global.entity.BaseTimeEntity;
import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.message.domain.Message;
import efub.backend.assignment.post.domain.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class MessageRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @OneToMany(mappedBy = "messageRoom")
    private List<Message> messages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "post_id")
    private Post post;

    @Builder
    public MessageRoom(Member sender, Member receiver, List messages, Post post) {
        this.sender = sender;
        this.receiver = receiver;
        this.messages = messages;
        this.post = post;
    } // 엔티티의 생성자를 만들 때, id 필드는 생략
}