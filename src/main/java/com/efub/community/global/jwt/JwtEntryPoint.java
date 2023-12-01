package com.efub.community.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }
        log.error("Unauthorized Error : {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "잘못된 아이디 또는 비밀번호입니다.");

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }
}