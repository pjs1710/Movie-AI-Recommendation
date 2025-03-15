package com.movie_ai_recommend.movie_ai_recommend.controller.gemini;

import com.movie_ai_recommend.movie_ai_recommend.dto.gemini.GeminiDto;
import com.movie_ai_recommend.movie_ai_recommend.service.gemini.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Gemini API
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService geminiService;

    @PostMapping("/ask")
    public ResponseEntity<?> askGemini(@RequestBody GeminiDto geminiDto) {
        GeminiDto resonse = geminiService.getAnswer(geminiDto, geminiDto.getUserId());
        return ResponseEntity.ok(resonse);
    }

}
