package com.movie_ai_recommend.movie_ai_recommend.service.gemini;

import com.movie_ai_recommend.movie_ai_recommend.dto.gemini.GeminiRequestDto;
import com.movie_ai_recommend.movie_ai_recommend.dto.gemini.GeminiResponseDto;

public interface GeminiService {

    GeminiResponseDto getAnswer(GeminiRequestDto geminiRequestDto);
}
