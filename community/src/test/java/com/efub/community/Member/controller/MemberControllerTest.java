package com.efub.community.Member.controller;

import com.efub.community.Member.dto.RefreshTokenRequestDto;
import com.efub.community.Member.dto.SignInRequestDto;
import com.efub.community.Member.dto.SignInResponseDto;
import com.efub.community.Member.dto.SignUpRequestDto;
import com.efub.community.Member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MemberControllerTest {
    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(memberController)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8"); // 응답 인코딩 설정
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @Test
    void signUpSuccess() throws Exception{
        SignUpRequestDto requestDto = new SignUpRequestDto();
        requestDto.setEmail("test@email.com");
        requestDto.setPw("password");
        requestDto.setNickname("nickname");
        requestDto.setUniv("university");
        requestDto.setStudentId("studentId");

        when(memberService.signup(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn("회원 가입이 완료되었습니다.");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("회원 가입이 완료되었습니다."));
    }

    @Test
    void signUpFailure() throws Exception{
        SignUpRequestDto requestDto = new SignUpRequestDto();
        requestDto.setEmail("test@email.com");
        requestDto.setPw("password");
        requestDto.setNickname("nickname");
        requestDto.setUniv("university");
        requestDto.setStudentId("studentId");

        when(memberService.signup(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("이미 존재하는 이메일입니다."));

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertEquals("이미 존재하는 이메일입니다.", result.getResolvedException().getMessage()));
    }

    @Test
    void signInSuccess() throws Exception{
        SignInRequestDto requestDto = new SignInRequestDto();
        requestDto.setEmail("test@email.com");
        requestDto.setPw("password");

        SignInResponseDto responseDto = new SignInResponseDto();
        responseDto.setEmail("test@email.com");
        responseDto.setAccessToken("testAccessToken");
        responseDto.setRefreshToken("testRefreshToken");

        when(memberService.signin(anyString(), anyString())).thenReturn(responseDto);

        mockMvc.perform(post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@email.com"))
                .andExpect(jsonPath("$.accessToken").value("testAccessToken"))
                .andExpect(jsonPath("$.refreshToken").value("testRefreshToken"));
    }

    @Test
    void signInFailure() throws Exception{
        SignInRequestDto requestDto = new SignInRequestDto();
        requestDto.setEmail("test@email.com");
        requestDto.setPw("wrongpassword");

        when(memberService.signin(anyString(), anyString()))
                .thenThrow(new RuntimeException("잘못된 비밀번호입니다!"));

        mockMvc.perform(post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertEquals("잘못된 비밀번호입니다!", result.getResolvedException().getMessage()));
    }

    @Test
    void refreshTokenSuccess() throws Exception{
        RefreshTokenRequestDto requestDto = new RefreshTokenRequestDto();
        requestDto.setRefreshToken("validRefreshToken");

        SignInResponseDto responseDto = new SignInResponseDto();
        responseDto.setEmail("test@email.com");
        responseDto.setAccessToken("newAccessToken");
        responseDto.setRefreshToken("validRefreshToken");

        when(memberService.requestRefresh(anyString())).thenReturn(responseDto);

        mockMvc.perform(post("/auth/refreshtoken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@email.com"))
                .andExpect(jsonPath("$.accessToken").value("newAccessToken"))
                .andExpect(jsonPath("$.refreshToken").value("validRefreshToken"));
    }

    @Test
    void refreshTokenFailure() throws Exception{
        RefreshTokenRequestDto requestDto = new RefreshTokenRequestDto();
        requestDto.setRefreshToken("invalidRefreshToken");

        when(memberService.requestRefresh(anyString()))
                .thenThrow(new EntityNotFoundException("존재하지 않는 RefreshToken입니다!"));

        mockMvc.perform(post("/auth/refreshtoken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException))
                .andExpect(result -> assertEquals("존재하지 않는 RefreshToken입니다!", result.getResolvedException().getMessage()));
    }
}