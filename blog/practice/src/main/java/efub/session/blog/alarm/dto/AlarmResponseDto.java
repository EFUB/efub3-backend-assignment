package efub.session.blog.alarm.dto;

import efub.session.blog.alarm.domain.Alarm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlarmResponseDto {
    private String type;
    private String content;
    private LocalDateTime createDate;

    public AlarmResponseDto(String type, String content, LocalDateTime createDate) {
        this.type = type;
        this.content = content;
        this.createDate = createDate;
    }

    public static AlarmResponseDto from(Alarm alarm) {
        return new AlarmResponseDto(
                alarm.getType(),
                alarm.getContent(),
                alarm.getCreatedDate()
        );
    }
}
