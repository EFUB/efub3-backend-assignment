package efub.session.community.account.service;

import efub.session.community.account.domain.Member;
import efub.session.community.account.dto.MemberUpdateRequestDto;
import efub.session.community.account.dto.SignUpRequestDto;
import efub.session.community.account.repository.MemberRepository;
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
        if(existsByEmail(requestDto.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 email입니다. email="+requestDto.getEmail());
        }
        Member member = memberRepository.save(requestDto.toEntity());
        return member.getMemberId();
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

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