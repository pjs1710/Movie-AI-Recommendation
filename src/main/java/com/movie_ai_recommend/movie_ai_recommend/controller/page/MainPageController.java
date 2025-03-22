package com.movie_ai_recommend.movie_ai_recommend.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main Page API
 *
 * -> 로그인 시 Main 화면 렌더링 API
 */
@Controller
public class MainPageController {

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }
}
