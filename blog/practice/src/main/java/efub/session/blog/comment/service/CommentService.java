package efub.session.blog.comment.service;

import efub.session.blog.board.domain.Board;
import efub.session.blog.board.dto.BoardModifyRequestDto;
import efub.session.blog.comment.dto.CommentModifyRequestDto;
import efub.session.blog.comment.dto.CommentRequestDto;
import efub.session.blog.comment.repository.CommentRepository;
import efub.session.blog.member.domain.Member;
import efub.session.blog.member.service.MemberService;
import efub.session.blog.post.domain.Post;
import efub.session.blog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.xml.stream.events.Comment;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final MemberService memberService;

    //댓글 작성
    public Long createComment(Long postId, CommentRequestDto requestDto) {
        Post post = postService.findPost(postId);
        Member writer = MemberService.findMemberById(requestDto.getMembertId());
        return commentRepository.save(requestDto.toEntity(post, writer)).getCommentId();
    }

    //댓글 조회-ID별
    @Transactional(readOnly = true)
    public Comment findCommentById(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException(("존재하지 않는 댓글입니다. ID="+commentId)));
    }

    //댓글 목록 조회-작성자별
    @Transactional(readOnly = true)
    public List<Comment> findCommentListByWriter(Member writer){
        return commentRepository.findAllByWriter(writer);
    }

    //댓글 목록 조회-게시글별
    @Transactional(readOnly = true)
    public List<Comment> findCommentListByPost(Long postId){
        Post post=postService.findPost(postId);
        return commentRepository.findAllByPost(post);
    }

    public Comment modifyComment(Long commentId, CommentModifyRequestDto requestDto) {
        Comment comment = CommentRepository.findByCommentIdAndOwner_MemberId(commentId,requestDto.getMemberId())
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        Comment.modifiedComment(requestDto);
        return comment;
    }
}
