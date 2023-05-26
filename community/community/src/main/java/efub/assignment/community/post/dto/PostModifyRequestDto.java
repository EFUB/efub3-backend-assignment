package efub.assignment.community.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostModifyRequestDto {
    private Long boardId;
    private String title;
    private String content;
    private Long writerId;
    private Boolean isPrivate;
}
