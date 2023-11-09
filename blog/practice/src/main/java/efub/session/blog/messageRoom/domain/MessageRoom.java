package efub.session.blog.messageRoom.domain;

import efub.session.blog.global.BaseTimeEntity;
import efub.session.blog.member.domain.Member;
import efub.session.blog.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class MessageRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_room_id")
    private Long messageRoomId;

    @ManyToOne
    @JoinColumn(name="sender_id", referencedColumnName="member_id")
    private Member sender;

    @ManyToOne
    @JoinColumn(name="receiver_id",referencedColumnName = "member_id")
    private Member receiver;

    @ManyToOne
    @JoinColumn(name="post_id",referencedColumnName = "post_id")
    private Post postId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public MessageRoom(Member sender, Member receiver, Post postId, String content){
        this.sender=sender;
        this.receiver=receiver;
        this.postId=postId;
        this.content=content;
    }
}
