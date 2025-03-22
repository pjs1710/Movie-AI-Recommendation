package com.movie_ai_recommend.movie_ai_recommend.controller.preference;

import com.movie_ai_recommend.movie_ai_recommend.dto.preference.PreferenceDto;
import com.movie_ai_recommend.movie_ai_recommend.service.preference.PreferenceSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Preference API
 *
 * 1. 사용자의 선호도를 입력받아 저장 (Save)
 * 2. 사용자의 선호도를 조회(Get)
 * 3. 사용자의 선호도 변경사항 발생 시 갱신(Update)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/preference")
public class PreferenceController {

    private final PreferenceSerivce preferenceSerivce;

    @PostMapping("/save")
    public ResponseEntity<?> savePreference(@RequestBody PreferenceDto preferenceDto) {
        try {
            preferenceSerivce.savePreference(preferenceDto);
            Map<String, String> response = new HashMap<>();
            response.put("message", "선호도가 성공적으로 저장되었습니다!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "선호도 저장에 실패했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPreference(@RequestParam("userId") Long userId) {
        try {
            PreferenceDto preference = preferenceSerivce.getPreference(userId);
            return ResponseEntity.ok(preference);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "선호도 조회에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePreference(@RequestBody PreferenceDto preferenceDto) {
        try {
            preferenceSerivce.updatePreference(preferenceDto);
            Map<String, String> response = new HashMap<>();
            response.put("message", "선호도가 성공적으로 업데이트 되었습니다!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "선호도 업데이트에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
