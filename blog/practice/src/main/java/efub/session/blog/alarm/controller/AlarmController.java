package efub.session.blog.alarm.controller;

import efub.session.blog.alarm.domain.Alarm;
import efub.session.blog.alarm.dto.AlarmResponseDto;
import efub.session.blog.alarm.service.AlarmService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/alarms")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AlarmController {
    private final AlarmService alarmService;

    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<AlarmResponseDto> alarmListFind(@PathVariable Long memberId){
        List<Alarm> alarmList=alarmService.findAlarmList(memberId);
        return alarmList.stream().map(AlarmResponseDto::from).collect(Collectors.toList());
    }
}
