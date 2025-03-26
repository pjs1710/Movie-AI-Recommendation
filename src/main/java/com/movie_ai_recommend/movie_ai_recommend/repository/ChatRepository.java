package com.movie_ai_recommend.movie_ai_recommend.repository;

import com.movie_ai_recommend.movie_ai_recommend.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    /**
     * JPQL을 사용한 가장 최신의 Response 값 하나 조회하기
     *
     * @param userId
     * @return
     */
    @Query("SELECT c.response FROM Chat c WHERE c.user.id = :userId ORDER BY c.createdAt DESC limit 1")
    String findLatestResponseByUserId(@Param("userId") Long userId);
}
