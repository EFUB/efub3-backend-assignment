package efub.session.blog.alarm.service;

import efub.session.blog.alarm.domain.Alarm;
import efub.session.blog.alarm.repository.AlarmRepository;
import efub.session.blog.member.domain.Member;
import efub.session.blog.member.service.MemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public List<Alarm> findAlarmList(Long memberId){
        Member member = memberService.findMemberById(memberId);
        return alarmRepository.findAllByMember(member);
    }
}
