package efub.session.community.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequestDto {

    private Long boardId;

    private Long writerId;

    private Boolean anonymous;

    private String content;

    @Builder
    public PostCreateRequestDto (Long boardId, Long writerId, Boolean anonymous, String content) {
        this.boardId = boardId;
        this.writerId = writerId;
        this.anonymous = anonymous;
        this.content = content;
    }

}