package com.movie_ai_recommend.movie_ai_recommend.controller.gemini;

import com.movie_ai_recommend.movie_ai_recommend.dto.gemini.GeminiRequestDto;
import com.movie_ai_recommend.movie_ai_recommend.dto.gemini.GeminiResponseDto;
import com.movie_ai_recommend.movie_ai_recommend.service.gemini.GeminiService;
import lombok.RequiredArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Gemini API
 *
 * 1. Gemini AI 호출 API
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService geminiService;

    @PostMapping("/ask")
    public ResponseEntity<?> askGemini(@RequestBody GeminiRequestDto geminiRequestDto) {
        try {
            // 1. AI 응답(마크다운) 받기
            String aiResponse = geminiService.getAnswer(geminiRequestDto).getPrompt();

            // 2. 마크다운 → HTML 변환
            Parser parser = Parser.builder().build();
            Node document = parser.parse(aiResponse != null ? aiResponse : "");
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            String html = renderer.render(document);

            // 3. 응답에 HTML 포함
            Map<String, Object> result = new HashMap<>();
            result.put("answer", html);

            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gemini API 호출 실패: " + e.getMessage());
        }
    }
}
