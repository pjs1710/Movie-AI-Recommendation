package com.movie_ai_recommend.movie_ai_recommend.service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    /**
     * Value 어노테이션으로 민감한 정보를 직접 사용하지 않습니다.
     */
    @Value("${spring.mail.from}")
    private String fromEmail;

    /**
     * 이메일 전송 시 유저에게 전달되는 메세지 형식입니다.
     * @param to
     * @param code
     */
    @Override
    public void sendVerificationEmail(String to, String code) {
        String subject = "[Movie AI Recommendation] 회원가입 이메일 인증";
        String verificationUrl = "회원님의 인증번호는 : [" + code + "] 입니다.";
        String message = "안녕하세요!\n\n회원가입 인증을 위해 아래 인증번호를 입력해주세요.\n\n" + verificationUrl + "\n\n감사합니다.";

        sendSimpleMessage(to, subject, message);
    }

    /**
     * 실질적으로 직접 메일 전송을 하는 로직입니다.
     * @param to
     * @param subject
     * @param text
     */
    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail); // @Value 어노테이션
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }
}
