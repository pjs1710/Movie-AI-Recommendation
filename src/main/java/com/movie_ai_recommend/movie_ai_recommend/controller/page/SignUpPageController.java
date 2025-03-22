package com.movie_ai_recommend.movie_ai_recommend.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Signup - Login Page API
 *
 * -> 회원가입 / 로그인 페이지 렌더링 API
 */

@Controller
public class SignUpPageController {

    @GetMapping("/")
    public String signupLogin() {
        return "user/signup-login";
    }
}