package com.movie_ai_recommend.movie_ai_recommend.dto;

import lombok.Data;

@Data
public class UserDto {

    private String userName;
    private String password;
    private String email;
    private Integer verificationNumber;
}
