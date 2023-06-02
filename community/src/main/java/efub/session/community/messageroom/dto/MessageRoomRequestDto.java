package efub.session.community.messageroom.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoomRequestDto {
    private Long senderId; // 처음 보낸 사람 id
    private Long receiverId; // 처음 받는 사람 Id
    private String firstMessage;  // 첫 쪽지 내용

    private Long postId; // 쪽지가 시작된 글의 id

    public MessageRoomRequestDto(Long senderId,Long receiverId,String content,Long postId){
        this.senderId=senderId;
        this.receiverId=receiverId;
        this.firstMessage =content;
        this.postId=postId;
    }
}