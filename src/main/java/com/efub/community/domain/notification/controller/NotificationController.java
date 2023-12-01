package com.efub.community.domain.notification.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.efub.community.domain.notification.dto.NotificationListResponseDto;
import com.efub.community.domain.notification.entity.Notification;
import com.efub.community.domain.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

	private final NotificationService notificationService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public NotificationListResponseDto getNotificationList(@RequestParam final Long memberId) {
		List<Notification> notifications = notificationService.readAll(memberId);
		return NotificationListResponseDto.of(notifications);
	}
}
