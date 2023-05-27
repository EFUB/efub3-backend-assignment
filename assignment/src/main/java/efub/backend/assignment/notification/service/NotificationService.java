package efub.backend.assignment.notification.service;

import efub.backend.assignment.notification.domain.Notification;
import efub.backend.assignment.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }
}