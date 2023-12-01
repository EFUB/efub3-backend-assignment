package com.efub.community.domain.notification.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.service.MemberService;
import com.efub.community.domain.notification.entity.Notification;
import com.efub.community.domain.notification.entity.NotificationType;
import com.efub.community.domain.notification.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

	private final NotificationRepository notificationRepository;
	private final MemberService memberService;

	public List<Notification> readAll(Long currentMemberId) {
		Member member = memberService.findById(currentMemberId);
		List<Notification> notifications = findByMemberId(member);
		return notifications != null ? notifications : Collections.emptyList();
	}

	public void createNotification(NotificationType notificationType, Member member) {
		Notification notification = Notification.builder()
			.notificationType(notificationType)
			.member(member)
			.build();
		notificationRepository.save(notification);
	}

	@Transactional(readOnly = true)
	public List<Notification> findByMemberId(Member member) {
		return notificationRepository.findByMember(member);
	}
}
