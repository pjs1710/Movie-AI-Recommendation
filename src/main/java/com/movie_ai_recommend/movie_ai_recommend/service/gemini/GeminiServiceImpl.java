package com.movie_ai_recommend.movie_ai_recommend.service.gemini;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie_ai_recommend.movie_ai_recommend.dto.gemini.GeminiDto;
import com.movie_ai_recommend.movie_ai_recommend.entity.Chat;
import com.movie_ai_recommend.movie_ai_recommend.entity.User;
import com.movie_ai_recommend.movie_ai_recommend.repository.ChatRepository;
import com.movie_ai_recommend.movie_ai_recommend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static org.springframework.web.reactive.function.client.WebClient.*;

@Service
public class GeminiServiceImpl implements GeminiService {

    /**
     * Value 어노테이션으로 민감한 정보를 직접 사용하지 않습니다.
     */
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public GeminiServiceImpl(Builder webClient, ChatRepository chatRepository, UserRepository userRepository) {
        this.webClient = webClient.build();
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public GeminiDto getAnswer(GeminiDto geminiDto, Long userId) {

        /**
         * 사용자 조회 로직
         */
        User user = userRepository.findById(geminiDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        /**
         * Request Payload 구성
         */
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", geminiDto.getPrompt())
                        })
                }
        );

        /**
         * API 호출
         */
        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseBody = objectMapper.readValue(response, new TypeReference<>() {});

            /**
             * "candidates" 리스트에서 첫 번째 객체 가져오기
             */
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
            if (candidates == null || candidates.isEmpty()) {
                throw new IllegalArgumentException("candidates가 비어있습니다.");
            }

            /**
             * "content" 내부의 "parts"에서 "text" 값 가져오기
             */
            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");

            if (parts == null || parts.isEmpty()) {
                throw new IllegalArgumentException("parts가 비어있습니다.");
            }

            /**
             * "text" 값 가져와서 "prompt" 키로 응답
             */
            String text = parts.get(0).get("text").toString();

            /**
             * 채팅 내용 저장
             */
            Chat chat = new Chat(user, geminiDto.getPrompt(), text);
            chatRepository.save(chat);
            GeminiDto resultDto = new GeminiDto(geminiDto.getUserId(), text);
            /**
             * DTO 반환
             */
            return resultDto;
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Json 파싱 에러입니다.");
        }
    }
}
