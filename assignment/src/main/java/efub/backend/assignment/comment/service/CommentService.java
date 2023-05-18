package efub.backend.assignment.comment.service;

import efub.backend.assignment.comment.domain.Comment;
import efub.backend.assignment.comment.dto.CommentModifyRequestDto;
import efub.backend.assignment.comment.dto.CommentRequestDto;
import efub.backend.assignment.comment.repository.CommentRepository;
import efub.backend.assignment.member.domain.Member;
import efub.backend.assignment.member.repository.MemberRepository;
import efub.backend.assignment.member.service.MemberService;
import efub.backend.assignment.post.domain.Post;
import efub.backend.assignment.post.repository.PostRepository;
import efub.backend.assignment.post.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final MemberService memberService;

    // 댓글 작성
    public Long createComment(Long postId, CommentRequestDto requestDto){
        Post post=postService.findPost(postId);
        Member writer = memberService.findMemberById(requestDto.getWriterId());

        return commentRepository.save(
                requestDto.toEntity(post,writer)).getCommentId();
    }

    // 댓글 조회 - ID별
    @Transactional(readOnly = true)
    public Comment findCommentById(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 댓글입니다. ID ="+commentId));
    }

    // 댓글 목록 조회 - 작성자별
    @Transactional(readOnly = true)
    public List<Comment> findCommentListByWriter(Member writer){

        return commentRepository.findAllByWriter(writer);
    }

    // 댓글 목록 조회 - 게시글별
    @Transactional(readOnly = true)
    public List<Comment> findCommentListByPost(Long postId){
        Post post=postService.findPost(postId);
        return commentRepository.findAllByPost(post);
    }

    // 댓글 수정
    public Comment modifyComment(Long commentId, CommentModifyRequestDto requestDto){
        Comment comment= commentRepository.findByCommentIdAndWriter_MemberId(commentId,requestDto.getWriterId())
                .orElseThrow(()-> new IllegalArgumentException("잘못된 접근입니다."));
        comment.updateComment(requestDto);
        return comment;

    }

    public void deleteComment(Long commentId){
        Comment comment = findCommentById(commentId);
        commentRepository.delete(comment);
    }

}
