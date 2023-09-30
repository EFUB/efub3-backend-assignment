package efub.backend.assignment.notification.controller;

import efub.backend.assignment.notification.domain.Notification;
import efub.backend.assignment.notification.dto.NotificationResponseDto;
import efub.backend.assignment.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NotificationResponseDto> getNotifications() {
        List<Notification> notificationList = notificationService.getNotificationsList();
        return notificationList.stream().map(NotificationResponseDto::from).collect(Collectors.toList());
    }
}
