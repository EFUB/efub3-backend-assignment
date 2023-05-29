package efub.session.community.alarm.domain;

import efub.session.community.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id", updatable = false)
    private Long alarmId;
    private String type;
    private String content;

    @Builder
    public Alarm(String type, String content) {
        this.type = type;
        this.content = content;
    }
}
