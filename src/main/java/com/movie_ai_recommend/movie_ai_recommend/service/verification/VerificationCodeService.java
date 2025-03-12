package com.movie_ai_recommend.movie_ai_recommend.service.verification;

import com.movie_ai_recommend.movie_ai_recommend.dto.VerificationCodeDto;
import com.movie_ai_recommend.movie_ai_recommend.entity.VerificationCode;

public interface VerificationCodeService {

    VerificationCode createVerificationCode(VerificationCodeDto verificationCodeDto);

    boolean verifyVerificationCode(VerificationCodeDto verificationCodeDto);
}
