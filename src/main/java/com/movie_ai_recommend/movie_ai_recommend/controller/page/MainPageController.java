package com.movie_ai_recommend.movie_ai_recommend.controller.page;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Main Page API
 *
 * -> 로그인 시 Main 화면 렌더링 API
 */
@Controller
public class MainPageController {

    @GetMapping("/main")
    public String mainPage(@RequestParam(value = "userId", required = false) Long userId, HttpSession session, Model model) {
        if (userId != null) {
            session.setAttribute("userId", userId);
        } else {
            userId = (Long) session.getAttribute("userId");
        }
        model.addAttribute("userId", userId);
        return "main";
    }
}
