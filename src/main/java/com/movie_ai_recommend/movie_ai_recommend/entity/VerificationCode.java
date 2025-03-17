package com.movie_ai_recommend.movie_ai_recommend.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 인증 번호에 대한 Entity
 */

@Entity
@Data
public class VerificationCode extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_code_id")
    private Long id;

    /*
    인증받을 이메일
     */
    @Column(name = "verification_code_email")
    private String email;

    /*
    인증번호
     */
    @Column(name = "verification_code_number")
    private String verificationNumber;

    /*
    인증유무
     */
    @Column(name = "is_verified")
    private boolean isVerified;

}