package com.movie_ai_recommend.movie_ai_recommend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Gemini API 호출에 대한 정보에 대한 Entity
 */
@Entity
@Data
@NoArgsConstructor
public class Chat extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /*
    유저가 질문할 프롬프트 내용
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String prompt;

    /*
    Gemini API가 응답하는 response
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String response;

    public Chat(User user, String prompt, String response) {
        this.user = user;
        this.prompt = prompt;
        this.response = response;
    }
}

