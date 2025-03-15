package com.movie_ai_recommend.movie_ai_recommend.dto.gemini;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeminiDto {

    private Long userId;
    private String prompt;
}
