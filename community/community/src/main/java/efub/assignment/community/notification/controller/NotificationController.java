package efub.assignment.community.notification.controller;

import efub.assignment.community.notification.domain.Notification;
import efub.assignment.community.notification.dto.NotificationResponseDto;
import efub.assignment.community.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<NotificationResponseDto> notificationListFind(@PathVariable Long memberId) {
        List<Notification> notificationList = notificationService.findNotificationList(memberId);
        return notificationList.stream().map(NotificationResponseDto::from).collect(Collectors.toList());
    }
}
