package efub.session.community.messageroom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageRoomFindRequestDto {
    private Long finderId; // 조회하는 사람
    private Long receiverId; // 받는 사람
    private Long postId; // 시작하는 글의 id

}
