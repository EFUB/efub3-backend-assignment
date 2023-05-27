package efub.backend.assignment.notification.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String boardName;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdTime;

    public Notification(String boardName, String type, String content, LocalDateTime createdTime) {
        this.boardName = boardName;
        this.type = type;
        this.content = content;
        this.createdTime = createdTime;
    }
}