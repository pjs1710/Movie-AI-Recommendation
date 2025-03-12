package com.movie_ai_recommend.movie_ai_recommend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    /*
    유저의 성함
     */
    @Column(name = "user_name")
    private String userName;

    /*
    유저의 패스워드
     */
    @Column(name = "user_password")
    private String password;

    /*
    유저의 이메일
     */
    @Column(name = "user_email")
    private String email;

}
