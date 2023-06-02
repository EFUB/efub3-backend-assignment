package efub.session.community.message.dto;

import efub.session.community.account.domain.Member;
import efub.session.community.message.domain.Message;
import efub.session.community.messageroom.domain.MessageRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRequestDto {
    @NotNull(message = "보내는 사람 ID를 입력하세요.")
    private Long senderId;

    @NotNull(message = "내용을 입력해주세요.")
    private String content;

    public Message toEntity(MessageRoom messageRoom,Member member){
        return Message.builder()
                .messageRoom(messageRoom)
                .sender(member)
                .content(this.content)
                .build();
    }
}