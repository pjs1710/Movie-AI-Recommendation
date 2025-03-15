package com.movie_ai_recommend.movie_ai_recommend.service.verification;

import com.movie_ai_recommend.movie_ai_recommend.dto.verification.VerificationCodeDto;
import com.movie_ai_recommend.movie_ai_recommend.entity.VerificationCode;
import com.movie_ai_recommend.movie_ai_recommend.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;

    /**
     * 유저의 이메일과 인증 번호를 저장해놓습니다.
     * 검증이 완료되기 전까지 is_verified 컬럼을 false로 지정합니다.
     * @param verificationCodeDto
     * @return
     */
    @Override
    public VerificationCode createVerificationCode(VerificationCodeDto verificationCodeDto) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(verificationCodeDto.getEmail());
        verificationCode.setVerificationNumber(verificationCodeDto.getVerificationNumber());
        verificationCode.setVerified(false);
        return verificationCodeRepository.save(verificationCode);
    }

    /**
     * DTO로부터 받은 Email과 VerificationCode를 통해 실제로 유효한 지 검증합니다.
     * 유효할 시, is_verified 컬럼을 true로 바꿉니다.
     * @param verificationCodeDto
     * @return
     */
    @Override
    public boolean verifyVerificationCode(VerificationCodeDto verificationCodeDto) {
        VerificationCode verificationCode = verificationCodeRepository.findByEmailAndVerificationNumber(verificationCodeDto.getEmail(), verificationCodeDto.getVerificationNumber());
        if (verificationCode != null && !verificationCode.isVerified()) {
            verificationCode.setVerified(true);
            verificationCodeRepository.save(verificationCode);
            return true;
        }
        return false;
    }
}
