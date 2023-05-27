package efub.backend.assignment.notification.dto;

import efub.backend.assignment.notification.domain.Notification;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class NotificationResponseDto {
    private String boardName;
    private String type;
    private String content;
    private LocalDateTime createdTime;

    public NotificationResponseDto(String boardName, String type, String content, LocalDateTime createdTime) {
        this.boardName = boardName;
        this.type = type;
        this.content = content;
        this.createdTime = createdTime;
    }

    public static List<NotificationResponseDto> from(List<Notification> notifications) {
        List<NotificationResponseDto> dtos = new ArrayList<>();

        for (Notification notification : notifications) {
            NotificationResponseDto dto = new NotificationResponseDto(
                    notification.getBoardName(),
                    notification.getType(),
                    notification.getContent(),
                    notification.getCreatedTime()
            );
            dtos.add(dto);
        }

        return dtos;
    }
}