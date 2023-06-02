package efub.assignment.community.notification.dto;

import efub.assignment.community.notification.domain.Notification;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationResponseDto {
    private String type;
    private String content;
    private LocalDateTime createdDate;

    public NotificationResponseDto(String type, String content, LocalDateTime createdDate) {
        this.type = type;
        this.content = content;
        this.createdDate = createdDate;
    }

    public static NotificationResponseDto from(Notification notification) {
        return new NotificationResponseDto(
                notification.getType(),
                notification.getContent(),
                notification.getCreatedDate()
        );
    }
}
