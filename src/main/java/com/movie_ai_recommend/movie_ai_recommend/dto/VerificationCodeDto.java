package com.movie_ai_recommend.movie_ai_recommend.dto;

import lombok.Data;

@Data
public class VerificationCodeDto {

    private String email;
    private Integer verificationNumber;
}
