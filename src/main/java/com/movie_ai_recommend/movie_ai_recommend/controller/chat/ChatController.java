package com.movie_ai_recommend.movie_ai_recommend.controller.chat;

import com.movie_ai_recommend.movie_ai_recommend.dto.gemini.GeminiRequestDto;
import com.movie_ai_recommend.movie_ai_recommend.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Chat API
 *
 * 1. Gemini AI Response Data 조회
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatRepository chatRepository;

    @GetMapping("/latest-response")
    public ResponseEntity<?> getLatestResponse(@RequestBody GeminiRequestDto geminiRequestDto) {
        try {
            String response = chatRepository.findLatestResponseByUserId(geminiRequestDto.getUserId());

            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("최신 응답 조회 중 오류 발생: " + e.getMessage());
        }
    }

}
