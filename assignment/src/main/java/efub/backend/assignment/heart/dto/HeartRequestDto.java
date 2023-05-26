package efub.backend.assignment.heart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartRequestDto {

    // 작성자, not null
    @NotNull(message = "작성자는 필수로 입력되어야 합니다.")
    private Long memberId;

    // builder 패턴 적용
    @Builder
    public HeartRequestDto(Long memberId) {
        this.memberId = memberId;
    }

}