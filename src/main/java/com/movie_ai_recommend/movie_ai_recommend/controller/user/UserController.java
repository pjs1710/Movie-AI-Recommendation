package com.movie_ai_recommend.movie_ai_recommend.controller.user;

import com.movie_ai_recommend.movie_ai_recommend.dto.user.UserDto;
import com.movie_ai_recommend.movie_ai_recommend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User API
 *
 * 1. 이메일 인증 번호 전송 API
 * 2. 인증 번호 검증 및 회원 등록 API
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/verify")
    public ResponseEntity<String> sendVerificationEmail(@RequestBody UserDto userDto) {
        try {
            userService.sendVerificationEmail(userDto);
            return ResponseEntity.ok("이메일 인증 번호를 전송했습니다. 이메일을 확인하여 인증을 완료해주세요.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 인증 번호 전송 실패: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        try {
            userService.verifyAndRegisterUser(userDto);
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패: " + e.getMessage());
        }
    }
}
