package efub.assignment.community.notification.controller;

import efub.assignment.community.notification.domain.Notification;
import efub.assignment.community.notification.dto.NotificationResponseDto;
import efub.assignment.community.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<NotificationResponseDto> notificationListFind() {
        List<Notification> notificationList = notificationService.findNotificationList();
        List<NotificationResponseDto> notificationResponseDtoList = new ArrayList<>();
        for(Notification notification:notificationList){
            notificationResponseDtoList.add(
                    new NotificationResponseDto(notification)
            );
        }
        return notificationResponseDtoList;
    }
}
