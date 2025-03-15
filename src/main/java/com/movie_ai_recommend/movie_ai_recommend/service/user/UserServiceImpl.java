package com.movie_ai_recommend.movie_ai_recommend.service.user;

import com.movie_ai_recommend.movie_ai_recommend.dto.user.UserDto;
import com.movie_ai_recommend.movie_ai_recommend.dto.verification.VerificationCodeDto;
import com.movie_ai_recommend.movie_ai_recommend.entity.User;
import com.movie_ai_recommend.movie_ai_recommend.repository.UserRepository;
import com.movie_ai_recommend.movie_ai_recommend.service.email.EmailService;
import com.movie_ai_recommend.movie_ai_recommend.service.verification.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VerificationCodeService verificationCodeService;
    private final EmailService emailService;

    /**
     * 예외 처리 및 이메일 전송 로직 구현부입니다.
     * 6자리의 난수를 생성해 유저에게 전달하고, 해당 데이터를 DB에 저장해 놓습니다.
     * @param userDto
     */
    @Override
    public void sendVerificationEmail(UserDto userDto) {
        if (!isUserNameAvailable(userDto.getUserName())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }

        Random random = new Random();
        int verificationNumber = random.nextInt(900000) + 100000;

        VerificationCodeDto verificationCodeDto = new VerificationCodeDto();
        verificationCodeDto.setEmail(userDto.getEmail());
        verificationCodeDto.setVerificationNumber(verificationNumber);
        verificationCodeService.createVerificationCode(verificationCodeDto);

        emailService.sendVerificationEmail(userDto.getEmail(), String.valueOf(verificationNumber));
    }

    /**
     * 인증 번호 검증 로직입니다. 유효하지 않다면 실패로 처리합니다.
     * 저장된 데이터와 인증 번호가 일치하다면 회원 가입을 처리합니다.
     * @param userDto
     */
    @Override
    public void verifyAndRegisterUser(UserDto userDto) {
        VerificationCodeDto verificationCodeDto = new VerificationCodeDto();
        verificationCodeDto.setEmail(userDto.getEmail());
        verificationCodeDto.setVerificationNumber(userDto.getVerificationNumber());

        if (verificationCodeService.verifyVerificationCode(verificationCodeDto)) {
            User user = new User();
            user.setUserInfo(userDto.getUserName(), userDto.getPassword(), userDto.getEmail());
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("이메일 인증에 실패했습니다.");
        }
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean isUserNameAvailable(String userName) {
        return userRepository.findByUserName(userName) == null;
    }
}
