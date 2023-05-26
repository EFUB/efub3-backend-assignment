package efub.assignment.community.comment.service;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.CommentModifyRequestDto;
import efub.assignment.community.comment.dto.CommentRequestDto;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Comment addComment(Long postId, CommentRequestDto requestDto){

        Member writer = memberRepository.findById(requestDto.getWriterId())
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 계정입니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 글입니다."));

        return commentRepository.save(
                Comment.builder()
                        .content(requestDto.getContent())
                        .writer(writer)
                        .post(post)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    // 댓글 조회 - ID별
    public Comment findComment(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 댓글입니다. id="+commentId));
    }

    @Transactional(readOnly = true)
    public List<Comment> findCommentList(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 글입니다."));

        return commentRepository.findAllByPost(post);
    }

    public Comment modifyComment(Long commentId, CommentModifyRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 댓글입니다."));

        if(comment.getWriter().getMemberId().equals(requestDto.getWriterId()))
            comment.modify(requestDto);
        else
            throw new IllegalArgumentException("댓글의 작성자가 아닙니다.");

        return comment;
    }

    public void removeComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 댓글입니다."));

        commentRepository.delete(comment);
    }
}
