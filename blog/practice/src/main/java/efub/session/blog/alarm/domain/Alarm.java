package efub.session.blog.alarm.domain;

import efub.session.blog.global.BaseTimeEntity;
import efub.session.blog.member.domain.Member;
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


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Alarm(String type, String content) {
        this.type = type;
        this.content = content;
    }
}
