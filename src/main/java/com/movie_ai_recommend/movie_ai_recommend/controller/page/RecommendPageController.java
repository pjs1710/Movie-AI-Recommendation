package com.movie_ai_recommend.movie_ai_recommend.controller.page;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Recommend Page API
 *
 * -> 추천 영화 페이지 렌더링 API
 */
public class RecommendPageController {
     // 추천 영화 페이지 렌더링

     @GetMapping("/recommendation")
     public String recommendPage(@RequestParam("userId") Long userId, HttpSession session, Model model) {
         if (userId != null) {
             session.setAttribute("userId", userId);
         } else {
             userId = (Long) session.getAttribute("userId");
         }
         model.addAttribute("userId", userId);
         return "recommend";
     }
}
