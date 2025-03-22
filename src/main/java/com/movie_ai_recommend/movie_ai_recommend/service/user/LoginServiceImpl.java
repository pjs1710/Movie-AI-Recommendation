package com.movie_ai_recommend.movie_ai_recommend.service.user;

import com.movie_ai_recommend.movie_ai_recommend.dto.user.LoginDto;
import com.movie_ai_recommend.movie_ai_recommend.entity.User;
import com.movie_ai_recommend.movie_ai_recommend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    /**
     * 로그인 처리 구현부입니다.
     * @param loginDto
     */
    @Override
    @Transactional
    public User login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElse(null);
        /*
         보안을 위해 이메일 OR 비밀번호가 틀렸다는 메세지 전달
        */
        if (user == null || !user.getPassword().equals(loginDto.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}
