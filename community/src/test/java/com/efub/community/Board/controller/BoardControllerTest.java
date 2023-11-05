package com.efub.community.Board.controller;

import com.efub.community.Board.domain.Board;
import com.efub.community.Board.dto.BoardCreateRequestDto;
import com.efub.community.Board.service.BoardService;
import com.efub.community.Member.domain.Member;
import com.efub.community.Member.service.MemberService;
import com.efub.community.Post.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BoardControllerTest {
    @Mock
    private BoardService boardService;
    @Mock
    private MemberService memberService;

    @Mock
    private PostService postService;

    @InjectMocks
    private BoardController boardController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }

    @Test
    void createBoardSuccess() throws Exception {
        // given
        BoardCreateRequestDto requestDto = new BoardCreateRequestDto();

        requestDto.setBoardName("Sample Board");
        requestDto.setBoardDesc("Sample Description");
        requestDto.setNotice("Sample Notice");
        requestDto.setOwnerId(1L);

        Member owner = new Member();
        owner.setMemberId(requestDto.getOwnerId());

        given(memberService.findMemberByEmail(any(String.class))).willReturn(owner);
        given(boardService.createBoard(any(BoardCreateRequestDto.class))).willAnswer(invocation -> {
            BoardCreateRequestDto dto = invocation.getArgument(0, BoardCreateRequestDto.class);
            return new Board(dto.getOwnerId(), dto.getBoardName(), dto.getBoardDesc(), dto.getNotice(), owner);
        });

        // when & then
        mockMvc.perform(post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void createBoardFailure() throws Exception {
        // given
        BoardCreateRequestDto requestDto = new BoardCreateRequestDto();
        requestDto.setBoardName("Sample Board");
        requestDto.setBoardDesc("Sample Description");
        requestDto.setNotice("Sample Notice");
        requestDto.setOwnerId(1L);

        when(boardService.createBoard(any(BoardCreateRequestDto.class))).thenThrow(RuntimeException.class);

        // when & then
        mockMvc.perform(post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }
}