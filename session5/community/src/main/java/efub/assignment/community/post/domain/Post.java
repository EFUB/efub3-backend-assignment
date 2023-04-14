package efub.assignment.community.post.domain;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.global.BaseTimeEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.dto.PostModifyRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/*
{
		"boardName": "벗들의 맛집",
		"writter" : "200"
		"writterShow" : "true",
		"content" : "낭만식탁 맛조항요~",
		"created": "2023-03-24",
		"updated": "2023-03-24"
		"postID" : "20"
}
 */
@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    @Column
    private boolean writerShow;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Post(Long postId,Board board,Member writer,boolean writerShow,String content){
        this.postId=postId;
        this.board=board;
        this.writer=writer;
        this.writerShow=writerShow;
        this.content=content;
    }


    public void updatePost(PostModifyRequestDto requestDto) {
        this.content=requestDto.getContent();
    }
}
