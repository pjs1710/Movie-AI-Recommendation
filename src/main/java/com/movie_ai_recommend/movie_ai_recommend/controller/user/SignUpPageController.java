package com.movie_ai_recommend.movie_ai_recommend.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpPageController {

    @GetMapping("/signup-login")
    public String signupLogin() {
        return "user/signup-login";
    }
}