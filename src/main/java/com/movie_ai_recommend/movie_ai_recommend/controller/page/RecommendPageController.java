package com.movie_ai_recommend.movie_ai_recommend.controller.page;

import com.movie_ai_recommend.movie_ai_recommend.repository.ChatRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Recommend Page API
 *
 * -> 추천 영화 페이지 렌더링 API
 */
@Controller
@RequiredArgsConstructor
public class RecommendPageController {
    private final ChatRepository chatRepository;

    @GetMapping("/recommendation")
    public String recommend(@RequestParam(value = "userId", required = false) Long userId,
                            HttpSession session, Model model) {
        if (userId == null) {
            userId = (Long) session.getAttribute("userId");
        } else {
            session.setAttribute("userId", userId);
        }
        model.addAttribute("userId", userId);

        String aiResponse = null;
        if (userId != null) {
            aiResponse = chatRepository.findLatestResponseByUserId(userId);
            if (aiResponse != null) {
                Parser parser = Parser.builder().build();
                Node document = parser.parse(aiResponse);
                HtmlRenderer renderer = HtmlRenderer.builder().build();
                aiResponse = renderer.render(document); // 마크다운 -> HTML 변환
            }
        }
        model.addAttribute("aiResponse", aiResponse);

        return "recommend/recommend";
    }
}