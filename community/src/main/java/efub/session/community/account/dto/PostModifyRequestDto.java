package efub.session.community.account.dto;

import lombok.Getter;

@Getter
public class PostModifyRequestDto {
    private Long accountId;
    private String title;
    private String content;


}
