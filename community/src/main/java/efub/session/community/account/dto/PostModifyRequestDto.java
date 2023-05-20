package efub.session.community.account.dto;

import lombok.Getter;

@Getter
public class PostModifyRequestDto {
    private Long memberId;
    private String title;
    private String content;


}
