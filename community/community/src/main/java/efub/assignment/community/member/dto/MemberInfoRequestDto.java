package efub.assignment.community.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class MemberInfoRequestDto {
    @NotNull(message = "작성자는 필수로 입력되어야 합니다.")
    private Long memberId;

    public MemberInfoRequestDto(Long memberId) {
        this.memberId = memberId;
    }
}
