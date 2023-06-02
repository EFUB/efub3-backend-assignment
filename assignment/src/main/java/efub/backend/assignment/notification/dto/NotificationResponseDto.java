package efub.backend.assignment.notification.dto;

import efub.backend.assignment.notification.domain.Notification;
import lombok.Getter;
import java.time.LocalDateTime;


@Getter
public class NotificationResponseDto {
    private String type;
    private String content;
    private LocalDateTime createdTime;

    public NotificationResponseDto(String type, String content, LocalDateTime createdTime) {
        this.type = type;
        this.content = content;
        this.createdTime = createdTime;
    }

    public static NotificationResponseDto from(Notification notification){
        return new NotificationResponseDto(
                notification.getType(),
                notification.getContent(),
                notification.getCreatedTime()
        );
    }
}