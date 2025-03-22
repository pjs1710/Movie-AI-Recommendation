package com.movie_ai_recommend.movie_ai_recommend.controller.user;

import com.movie_ai_recommend.movie_ai_recommend.dto.user.LoginDto;
import com.movie_ai_recommend.movie_ai_recommend.entity.User;
import com.movie_ai_recommend.movie_ai_recommend.service.user.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Login API
 *
 * 1. 로그인 성공 시 Main Page -> Redirect
 * 2. 로그인 실패 시 기존 페이지로 렌더링
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public String login(LoginDto loginDto, HttpSession session, Model model) {
        try {
            User user = loginService.login(loginDto);
            session.setAttribute("userId", user.getId()); //* 세션에 userId 저장
            return "redirect:/main?userId=" + user.getId(); //* userId를 쿼리 파라미터로 전달
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "user/signup-login";
        }
    }
}
