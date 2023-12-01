package com.efub.community.domain.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efub.community.domain.board.domain.Post;
import com.efub.community.domain.chat.domain.MessageRoom;
import com.efub.community.domain.member.domain.Member;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {

	List<MessageRoom> findByInitialSenderOrInitialReceiver(Member sender, Member receiver);

	Boolean existsByInitialSenderAndInitialReceiverAndCreatedFrom(Member senderId, Member receiverId, Post createdFrom);
}
