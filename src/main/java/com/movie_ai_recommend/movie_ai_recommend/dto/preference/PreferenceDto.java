package com.movie_ai_recommend.movie_ai_recommend.dto.preference;

import com.movie_ai_recommend.movie_ai_recommend.entity.Genre;
import lombok.Data;

@Data
public class PreferenceDto {

    private Long userId;
    private Genre favoriteGenre;
    private String favoriteActor;
    private String favoriteDirector;
    private String recentWatched;
}
