package efub.assignment.community.notification.domain;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.global.entity.BaseTimeEntity;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", updatable = false)
    private Long notificationId;

    @Column
    private String boardNameORMessageRoom;

    @Column
    private String content;

    @Builder
    public Notification(String boardNameORMessageRoom, String content){
        this.boardNameORMessageRoom = boardNameORMessageRoom;
        this.content = content;
    }
}
