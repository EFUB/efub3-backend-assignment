package efub.session.community.comment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 댓글의 내용 변경 요청을 보낼 때 사용하는 DTO
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentModifyRequestDto {

    private String content;
    private Long memberId;

    @Builder
    public CommentModifyRequestDto (String content) {
        this.content = content;
    }
}