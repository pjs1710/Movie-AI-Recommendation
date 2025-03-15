package com.movie_ai_recommend.movie_ai_recommend.service.user;

import com.movie_ai_recommend.movie_ai_recommend.dto.user.UserDto;
import com.movie_ai_recommend.movie_ai_recommend.entity.User;

public interface UserService {
    void sendVerificationEmail(UserDto userDto);

    void verifyAndRegisterUser(UserDto userDto);

    User findByUserName(String userName);

    boolean isUserNameAvailable(String userName);
}
