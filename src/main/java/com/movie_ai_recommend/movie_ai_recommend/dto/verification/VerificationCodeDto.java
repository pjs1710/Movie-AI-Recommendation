package com.movie_ai_recommend.movie_ai_recommend.dto.verification;

import lombok.Data;

@Data
public class VerificationCodeDto {

    private String email;
    private String verificationNumber;
}