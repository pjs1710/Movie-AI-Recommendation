package com.movie_ai_recommend.movie_ai_recommend.service.gemini;

import com.movie_ai_recommend.movie_ai_recommend.dto.gemini.GeminiDto;

public interface GeminiService {

    GeminiDto getAnswer(GeminiDto geminiDto, Long userId);
}
