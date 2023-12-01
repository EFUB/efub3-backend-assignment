package com.efub.community.domain.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	List<Notification> findByMember(Member member);
}
