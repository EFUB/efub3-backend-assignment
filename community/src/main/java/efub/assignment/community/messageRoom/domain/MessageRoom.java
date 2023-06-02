package efub.assignment.community.messageRoom.domain;

import efub.assignment.community.global.entity.BaseTimeEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_room_id")
    private Long messageRoomId;

    @NotNull(message = "내용은 필수로 입력되어야 합니다.")
    @Column(updatable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "creater_id", updatable = false)
    private Member creater;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "receiver_id", updatable = false)
    private Member receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "post_id", updatable = false)
    private Post post;

    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL)
    private List<Message> messageList = new ArrayList<>();

    @Builder
    public MessageRoom(String content, Member creater, Member receiver, Post post){
        this.content = content;
        this.creater = creater;
        this.receiver = receiver;
        this.post = post;
    }
}
