package efub.backend.assignment.notification.repository;

import efub.backend.assignment.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}