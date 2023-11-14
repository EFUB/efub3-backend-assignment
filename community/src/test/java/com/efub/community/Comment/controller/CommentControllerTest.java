package com.efub.community.Comment.controller;

import com.efub.community.Comment.domain.Comment;
import com.efub.community.Comment.dto.CommentRequestDto;
import com.efub.community.Comment.dto.CommentUpdateRequestDto;
import com.efub.community.Comment.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentControllerTest {

    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentService commentService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    void commentFind_Success() throws Exception {
        Comment comment = new Comment();
        given(commentService.findComment(1L)).willReturn(comment);

        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk());

        // Additional assertions can be added to verify the response.
    }

    @Test
    void commentFind_Failure() throws Exception {
        given(commentService.findComment(1L)).willReturn(null);

        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isNotFound());

        // Additional assertions can be added to verify the response.
    }

    @Test
    void commentListFind_Success() throws Exception {
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        given(commentService.findCommentList(1L)).willReturn(Arrays.asList(comment1, comment2));

        mockMvc.perform(get("/comments/posts/1"))
                .andExpect(status().isOk());

        // Additional assertions can be added to verify the response.
    }

    @Test
    void commentListFind_Failure() throws Exception {
        given(commentService.findCommentList(1L)).willReturn(null);

        mockMvc.perform(get("/comments/posts/1"))
                .andExpect(status().isNotFound());

        // Additional assertions can be added to verify the response.
    }

    @Test
    void commentCreate_Success() throws Exception {
        Comment comment = new Comment();
        CommentRequestDto dto = new CommentRequestDto();
        given(commentService.createComment(1L, dto)).willReturn(comment);

        mockMvc.perform(post("/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))  // Your JSON content here
                .andExpect(status().isCreated());

        // Additional assertions can be added to verify the response.
    }

    @Test
    void commentCreate_Failure() throws Exception {
        CommentRequestDto dto = new CommentRequestDto();
        given(commentService.createComment(1L, dto)).willReturn(null);

        mockMvc.perform(post("/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))  // Your JSON content here
                .andExpect(status().isBadRequest());

        // Additional assertions can be added to verify the response.
    }

    @Test
    void commentUpdate_Success() throws Exception {
        Comment comment = new Comment();
        CommentUpdateRequestDto dto = new CommentUpdateRequestDto();
        given(commentService.updateComment(1L, dto)).willReturn(comment);

        mockMvc.perform(put("/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))  // Your JSON content here
                .andExpect(status().isOk());

        // Additional assertions can be added to verify the response.
    }

    @Test
    void commentUpdate_Failure() throws Exception {
        CommentUpdateRequestDto dto = new CommentUpdateRequestDto();
        given(commentService.updateComment(1L, dto)).willReturn(null);

        mockMvc.perform(put("/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))  // Your JSON content here
                .andExpect(status().isBadRequest());

        // Additional assertions can be added to verify the response.
    }

    @Test
    void commentDelete_Success() throws Exception {
        mockMvc.perform(delete("/comments/1"))
                .andExpect(status().isOk());

        // Additional assertions can be added to verify the response.
    }

//    @Test
//    void commentDelete_Failure() throws Exception {
//        given(commentService.deleteComment(1L)).willThrow(new RuntimeException("Error"));
//
//        mockMvc.perform(delete("/comments/1"))
//                .andExpect(status().isInternalServerError());
//
//    }
}