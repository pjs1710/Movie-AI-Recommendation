package com.movie_ai_recommend.movie_ai_recommend.controller.gemini;

import com.movie_ai_recommend.movie_ai_recommend.dto.gemini.GeminiDto;
import com.movie_ai_recommend.movie_ai_recommend.service.gemini.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> askGemini(@RequestBody GeminiDto geminiDto) {
        try {
            GeminiDto response = geminiService.getAnswer(geminiDto, geminiDto.getUserId());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gemini API 호출 실패: " + e.getMessage());
        }
    }

}
