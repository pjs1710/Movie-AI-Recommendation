package com.movie_ai_recommend.movie_ai_recommend.repository;

import com.movie_ai_recommend.movie_ai_recommend.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    VerificationCode findByEmailAndVerificationNumber(String email, String verificationNumber);
}