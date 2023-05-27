package efub.backend.assignment.notification.controller;

import efub.backend.assignment.notification.domain.Notification;
import efub.backend.assignment.notification.dto.NotificationResponseDto;
import efub.backend.assignment.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NotificationResponseDto> getNotifications() {
        List<Notification> notifications = notificationService.getNotifications();
        return NotificationResponseDto.from(notifications);
    }
}