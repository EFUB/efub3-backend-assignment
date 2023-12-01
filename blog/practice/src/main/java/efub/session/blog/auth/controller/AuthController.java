package efub.session.blog.auth.controller;

import efub.session.blog.auth.dto.AuthRequestDto;
import efub.session.blog.auth.dto.AuthResponseDto;
import efub.session.blog.auth.dto.JwtResponseDto;
import efub.session.blog.auth.service.AuthService;
import efub.session.blog.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService oAuthService;

    @ResponseBody
    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto oAuthRequestDto, HttpSession session) {
        AuthResponseDto member = oAuthService.logIn(oAuthRequestDto.getCode());

        if (member.getEmail() != null) {
            session.setAttribute("userId", member.getEmail());
            session.setAttribute("access_Token", member.getAccessToken());
        }

        return member;
    }
}
