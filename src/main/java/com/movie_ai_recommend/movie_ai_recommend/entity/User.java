package com.movie_ai_recommend.movie_ai_recommend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 유저 정보에 대한 Entity
 */

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

    /*
    유저 정보 Method
     */
    public void setUserInfo(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chats = new ArrayList<>();

}
