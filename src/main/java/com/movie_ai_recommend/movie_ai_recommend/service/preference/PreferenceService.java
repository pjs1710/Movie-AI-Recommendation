package com.movie_ai_recommend.movie_ai_recommend.service.preference;

import com.movie_ai_recommend.movie_ai_recommend.dto.preference.PreferenceDto;
import com.movie_ai_recommend.movie_ai_recommend.entity.Preference;

public interface PreferenceService {
    PreferenceDto getPreference(Long userId);

    Preference savePreference(PreferenceDto preferenceDto);

    Preference updatePreference(PreferenceDto preferenceDto);

    String composeMovieRecommendationPrompt(Long userId);
}
