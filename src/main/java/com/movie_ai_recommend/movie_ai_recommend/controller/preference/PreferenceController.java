package com.movie_ai_recommend.movie_ai_recommend.controller.preference;

import com.movie_ai_recommend.movie_ai_recommend.dto.preference.PreferenceDto;
import com.movie_ai_recommend.movie_ai_recommend.service.preference.PreferenceSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        preferenceSerivce.savePreference(preferenceDto);
        return ResponseEntity.ok("선호도가 성공적으로 저장되었습니다!");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPreference(@RequestParam("userId") Long userId) {
        PreferenceDto preference = preferenceSerivce.getPreference(userId);
        return ResponseEntity.ok(preference);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePreference(@RequestBody PreferenceDto preferenceDto) {
        preferenceSerivce.updatePreference(preferenceDto);
        return ResponseEntity.ok("선호도가 성공적으로 업데이트 되었습니다!");
    }
}
