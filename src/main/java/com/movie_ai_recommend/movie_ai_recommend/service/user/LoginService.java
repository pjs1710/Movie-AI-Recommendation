package com.movie_ai_recommend.movie_ai_recommend.service.user;

import com.movie_ai_recommend.movie_ai_recommend.dto.user.LoginDto;
import com.movie_ai_recommend.movie_ai_recommend.entity.User;

public interface LoginService {
    User login(LoginDto loginDto);
}
