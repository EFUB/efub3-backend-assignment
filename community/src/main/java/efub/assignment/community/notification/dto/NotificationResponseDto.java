package efub.assignment.community.notification.dto;

import efub.assignment.community.notification.domain.Notification;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NotificationResponseDto {
    private String boardNameORMessageRoom;
    private String content;
    private LocalDateTime createdDate;

    public NotificationResponseDto(Notification notification){
        this.boardNameORMessageRoom = notification.getBoardNameORMessageRoom();
        this.content = notification.getContent();
        this.createdDate = notification.getCreatedDate();
    }
}
