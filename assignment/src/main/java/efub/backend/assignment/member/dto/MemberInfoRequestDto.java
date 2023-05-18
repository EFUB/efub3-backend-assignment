package efub.backend.assignment.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoRequestDto {// 로그인 기능이 없어 회원 정보를 받아오는 임시 방편 클래스
    @NotNull(message =  "작성자는 필수로 입력되어야 합니다.")
    private Long memberId;

    public MemberInfoRequestDto(Long memberId){
        this.memberId = memberId;
    }
}
