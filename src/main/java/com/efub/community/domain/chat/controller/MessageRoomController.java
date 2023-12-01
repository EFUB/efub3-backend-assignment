package com.efub.community.domain.chat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.efub.community.domain.chat.domain.MessageRoom;
import com.efub.community.domain.chat.dto.MessageRoomListResponseDto;
import com.efub.community.domain.chat.dto.MessageRoomRequestDto;
import com.efub.community.domain.chat.dto.MessageRoomResponseDto;
import com.efub.community.domain.chat.service.MessageRoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/messagerooms")
public class MessageRoomController {

	private final MessageRoomService messageRoomService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public MessageRoomResponseDto createMessageRoom(@RequestBody @Valid final MessageRoomRequestDto requestDto) {
		Long id = messageRoomService.createMessageRoom(requestDto);
		MessageRoom messageRoom = messageRoomService.findById(id);
		return new MessageRoomResponseDto(messageRoom);
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public MessageRoomListResponseDto getMessageRoomList(@RequestParam final Long memberId) {
		List<MessageRoom> messageRoomList = messageRoomService.findByOwner(memberId);
		return MessageRoomListResponseDto.of(messageRoomList);
	}

	@GetMapping("/check")
	@ResponseStatus(value = HttpStatus.OK)
	public String checkMessageRoom(@RequestParam final Long senderId, @RequestParam final Long receiverId,
		@RequestParam final Long createdFrom) {
		return messageRoomService.checkMessageRoom(senderId, receiverId, createdFrom);
	}

	@DeleteMapping("/{messageRoomId}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteMessageRoom(@PathVariable final Long messageRoomId, @RequestParam final Long memberId) {
		messageRoomService.delete(messageRoomId, memberId);
		return "성공적으로 삭제되었습니다.";
	}
}
