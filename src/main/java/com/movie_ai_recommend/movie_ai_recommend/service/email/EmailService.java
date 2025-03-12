package com.movie_ai_recommend.movie_ai_recommend.service.email;

public interface EmailService {

    void sendVerificationEmail(String to, String code);
}
