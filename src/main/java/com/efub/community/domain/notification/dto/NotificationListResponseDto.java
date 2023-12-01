package com.efub.community.domain.notification.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.efub.community.domain.notification.entity.Notification;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationListResponseDto {

	private List<SingleNotification> notifications;
	private Integer count;

	public static NotificationListResponseDto of(List<Notification> notificationList) {
		return NotificationListResponseDto.builder()
			.notifications(notificationList.stream()
				.map(NotificationListResponseDto.SingleNotification::of)
				.collect(Collectors.toList()))
			.count(notificationList.size())
			.build();
	}

	@Getter
	public static class SingleNotification {
		private final String notificationType;
		private final String content;
		private final LocalDateTime createdDate;

		public SingleNotification(Notification notification) {
			this.notificationType = notification.getNotificationType().getTitle();
			this.content = notification.getNotificationType().getDescription();
			this.createdDate = notification.getCreatedDate();
		}

		public static NotificationListResponseDto.SingleNotification of(Notification notification) {
			return new NotificationListResponseDto.SingleNotification(notification);
		}
	}
}
