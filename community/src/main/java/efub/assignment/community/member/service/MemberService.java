package efub.assignment.community.member.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberUpdateRequestDto;
import efub.assignment.community.member.dto.SignUpRequestDto;
import efub.assignment.community.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long signUp(SignUpRequestDto requestDto){
        if(memberRepository.existsByEmail(requestDto.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 email입니다. email="+requestDto.getEmail());
        }

        if(memberRepository.existsByStudentId(requestDto.getStudentId())){
            throw new IllegalArgumentException("이미 존재하는 학번입니다. 학번="+requestDto.getStudentId());
        }

        Member member = memberRepository.save(requestDto.toEntity());
        return member.getMemberId();
    }

    /*@Transactional(readOnly = true)
    public boolean existsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    //학번 중복 체크 추가
    @Transactional(readOnly = true)
    public boolean existsByStudentId(String studentId) { return memberRepository.existsByStudentId(studentId); }*/

    @Transactional(readOnly = true)
    public Member findMemberById(Long id){
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Member를 찾을 수 없습니다. id="+id));
    }

    public Long update(Long memberId, MemberUpdateRequestDto requestDto){
        Member member = findMemberById(memberId);
        member.updateMember(requestDto.getNickname());
        return member.getMemberId();
    }

    public void withdraw(Long memberId){
        Member member = findMemberById(memberId);
        member.withdrawMember();
    }
}
