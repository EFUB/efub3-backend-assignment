package efub.session.community.heart.service;

import efub.session.community.comment.domain.Comment;
import efub.session.community.heart.domain.CommentHeart;
import efub.session.community.comment.repository.CommentHeartRepository;
import efub.session.community.account.domain.Member;
import efub.session.community.account.dto.MemberUpdateRequestDto;
import efub.session.community.account.service.MemberService;
import efub.session.community.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentHeartService {
    private final CommentService commentService;
    private final CommentHeartRepository commentHeartRepository;

    private final MemberService memberService;


    // 댓글 좋아요 생성
    public void create(Long commmentId, MemberUpdateRequestDto requestDto){
        Member member=memberService.findMemberById(requestDto.getMemberId());
        Comment comment=commentService.findCommentById(commmentId);
        if(isExistsByWriterAndComment(member,comment)){
            throw new RuntimeException("이미 좋아요를 눌렀습니다.");
        }

        CommentHeart commentHeart=CommentHeart.builder()
                .comment(comment)
                .member(member)
                .build();
        commentHeartRepository.save(commentHeart);

    }

    //댓글 좋아요 삭제
    public void delete(Long commentId, Long memberId){
        Member member=memberService.findMemberById(memberId);
        Comment comment=commentService.findCommentById(commentId);
        CommentHeart commentHeart=commentHeartRepository.findByWriterAndComment(member,comment)
                .orElseThrow(()->new IllegalArgumentException("해당 좋아요가 없습니다."));
        commentHeartRepository.delete(commentHeart);
    }

    //댓글 좋아요 조회
    @Transactional(readOnly = true)
    public boolean isExistsByWriterAndComment(Member member,Comment comment){
        return commentHeartRepository.existsByWriterAndComment(member,comment);
    }

    //댓글 좋아요 수 count
    @Transactional(readOnly = true)
    public Integer countCommentHeart(Comment comment){
        Integer count=commentHeartRepository.countByComment(comment);
        return count;

    }



}