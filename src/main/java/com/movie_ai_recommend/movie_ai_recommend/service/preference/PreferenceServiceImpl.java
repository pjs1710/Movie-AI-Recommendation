package com.movie_ai_recommend.movie_ai_recommend.service.preference;

import com.movie_ai_recommend.movie_ai_recommend.dto.preference.PreferenceDto;
import com.movie_ai_recommend.movie_ai_recommend.entity.Preference;
import com.movie_ai_recommend.movie_ai_recommend.entity.User;
import com.movie_ai_recommend.movie_ai_recommend.repository.PreferenceRepository;
import com.movie_ai_recommend.movie_ai_recommend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PreferenceServiceImpl implements PreferenceService {

    private final PreferenceRepository preferenceRepository;
    private final UserRepository userRepository;

    /**
     * 사용자의 선호도를 조회합니다.
     *
     * @param userId
     * @return
     */
    @Override
    public PreferenceDto getPreference(Long userId) {
        Preference preference = preferenceRepository.findByUserId(userId);
        if (preference == null){
            return null;
        }
        return convertToDto(preference);
    }

    /**
     * 존재하는 사용자의 선호도를 DTO를 통해 값을 전달받고, 해당 값을 선호도 Table에 저장합니다.
     *
     * @param preferenceDto
     * @return
     */
    @Override
    @Transactional
    public Preference savePreference(PreferenceDto preferenceDto) {
        User user = userRepository.findById(preferenceDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾지 못했습니다. " + preferenceDto.getUserId()));
        if (preferenceRepository.existsByUserId(preferenceDto.getUserId())) {
            throw new IllegalArgumentException("이미 선호도 정보가 존재합니다.");
        }
        Preference preference = new Preference(
                user,
                preferenceDto.getFavoriteGenre(),
                preferenceDto.getFavoriteActor(),
                preferenceDto.getFavoriteDirector(),
                preferenceDto.getRecentWatched()
        );

        return preferenceRepository.save(preference);
    }

    /**
     * 사용자의 선호도가 바뀌었을 경우 Update하는 로직을 구현.
     *
     * @param preferenceDto
     * @return
     */
    @Override
    @Transactional
    public Preference updatePreference(PreferenceDto preferenceDto) {
        Preference preference = preferenceRepository.findByUserId(preferenceDto.getUserId());
        if (preference == null) {
            throw new IllegalArgumentException("사용자를 찾지 못했습니다. " + preferenceDto.getUserId());
        }

        preference.setFavoriteGenre(preferenceDto.getFavoriteGenre());
        preference.setFavoriteActor(preferenceDto.getFavoriteActor());
        preference.setFavoriteDirector(preferenceDto.getFavoriteDirector());
        preference.setRecentWatched(preferenceDto.getRecentWatched());

        return preferenceRepository.save(preference);
    }

    /**
     * 사용자 맞춤형 영화 취향 Customizing Prompt를 구성하는 로직입니다.
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public String composeMovieRecommendationPrompt(Long userId) {
        Preference preference = preferenceRepository.findByUserId(userId);
        if (preference == null) {
            return "선호도 조사는 필수입니다.";
        }

        StringBuilder prompt = new StringBuilder();
        prompt.append("저의 영화 취향에 맞는 영화를 추천해주세요.\n");

        if (preference.getFavoriteGenre() != null) {
            prompt.append("선호하는 장르는 ").append(preference.getFavoriteGenre()).append("입니다.\n");
        }

        if (preference.getFavoriteActor() != null) {
            prompt.append("선호하는 배우는 ").append(preference.getFavoriteActor()).append("입니다.\n");
        }

        if (preference.getFavoriteDirector() != null) {
            prompt.append("선호하는 감독은 ").append(preference.getFavoriteDirector()).append("입니다.\n");
        }

        if (preference.getRecentWatched() != null) {
            prompt.append("최근에 가장 재밌게 본 영화는 ").append(preference.getRecentWatched()).append("입니다.\n");
        }

        prompt.append("이 정보를 바탕으로 제가 좋아할 만한 영화를 추천해주세요.");

        return prompt.toString();
    }

    private PreferenceDto convertToDto(Preference preference) {
        PreferenceDto preferenceDto = new PreferenceDto();
        preferenceDto.setUserId(preference.getUser().getId());
        preferenceDto.setFavoriteGenre(preference.getFavoriteGenre());
        preferenceDto.setFavoriteActor(preference.getFavoriteActor());
        preferenceDto.setFavoriteDirector(preference.getFavoriteDirector());
        preferenceDto.setRecentWatched(preference.getRecentWatched());
        return preferenceDto;
    }
}
