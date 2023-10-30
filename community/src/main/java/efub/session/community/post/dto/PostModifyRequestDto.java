package efub.session.community.post.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 게시글의 내용 변경 요청을 보낼 때 사용하는 DTO
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostModifyRequestDto {

    private String content;
    private Long memberId;
    private String title;

    @Builder
    public PostModifyRequestDto (String content) {
        this.content = content;
    }
    public PostModifyRequestDto(Long memberId, String content) {
        this.memberId = memberId;
        this.content = content;
    }

    // Getter 메서드
    public Long getMemberId() {
        return memberId;
    }

    public String getContent() {
        return content;
    }

    // Setter 메서드
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }
}