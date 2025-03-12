package com.movie_ai_recommend.movie_ai_recommend.controller;

import com.movie_ai_recommend.movie_ai_recommend.service.GeminiService;
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

}
