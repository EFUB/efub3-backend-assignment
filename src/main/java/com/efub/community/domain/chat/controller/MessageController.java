package com.efub.community.domain.chat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.efub.community.domain.chat.domain.Message;
import com.efub.community.domain.chat.domain.MessageRoom;
import com.efub.community.domain.chat.dto.MessageListResponseDto;
import com.efub.community.domain.chat.dto.MessageRequestDto;
import com.efub.community.domain.chat.dto.MessageResponseDto;
import com.efub.community.domain.chat.service.MessageRoomService;
import com.efub.community.domain.chat.service.MessageService;
import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/messagerooms/{messageRoomId}/messages")
public class MessageController {

	private final MessageService messageService;
	private final MemberService memberService;
	private final MessageRoomService messageRoomService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public MessageResponseDto createMessage(@PathVariable final Long messageRoomId,
		@RequestBody @Valid final MessageRequestDto requestDto) {
		Long id = messageService.createMessage(messageRoomId, requestDto);
		Message message = messageService.findById(id);
		return new MessageResponseDto(message);
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public MessageListResponseDto getMessageList(@PathVariable final Long messageRoomId,
		@RequestParam final Long memberId) {
		Member currentMember = memberService.findById(memberId);
		MessageRoom messageRoom = messageRoomService.findById(messageRoomId);
		List<Message> messageList = messageService.readMessages(messageRoom, currentMember);
		return MessageListResponseDto.of(messageList, messageRoom, currentMember);
	}

}
