package efub.assignment.community.comment.service;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.comment.dto.CommentRequestDto;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor    // 생성자를 통해 필요한 의존관계를 주입 받음
public class CommentService {
    private final CommentRepository commentRepository;  // 의존관계: CommentService -> CommentRepository

    private final PostService postService;      // 의존관계: CommentService -> PostService
    private final MemberService memberService;  // 의존관계: CommentService -> MmeberService

    // 댓글 작성
    public Long createComment(Long postId, CommentRequestDto requestDto) {
        Post post = postService.findPost(postId);
        Member writer = memberService.findMemberById(requestDto.getMemberId());
        return commentRepository.save(requestDto.toEntity(post, writer)).getCommentId();
    }

    // 댓글 조회 - ID별
    @Transactional(readOnly = true)
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글입니다. ID=" + commentId));
    }

    // 댓글 목록 조회 - 작성자별
    @Transactional(readOnly = true)
    public List<Comment> findCommentListByWriter(Member writer) {
        return commentRepository.findAllByWriter(writer);
    }

    // 댓글 목록 조회 - 게시글별
    @Transactional(readOnly = true)
    public List<Comment> findCommentListByPost(Long postId) {
        Post post = postService.findPost(postId);
        return commentRepository.findAllByPost(post);
    }

    // 댓글 수정
    public void updateComment(CommentRequestDto requestDto, Long commentId) {
        Comment comment = findCommentById(commentId);
        comment.updateComment(requestDto.getContent());
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        Comment comment = findCommentById(commentId);
        commentRepository.delete(comment);
    }
}
