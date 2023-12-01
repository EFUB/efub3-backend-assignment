package com.efub.community.domain.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efub.community.domain.chat.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByMessageRoomMessageRoomId(Long messageRoomId);
}
