package efub.assignment.community.messageRoom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/*
{
		"createrID" : "me123",
		"receiverID" : "you567",
		"postID" : 56,
		"content" : "안녕하세요."
}
 */

@Getter
@NoArgsConstructor
public class MessageRoomCreateRequestDto {
    private Long createrId;
    private Long receiverId;
    private Long postId;
    private String content;
}
