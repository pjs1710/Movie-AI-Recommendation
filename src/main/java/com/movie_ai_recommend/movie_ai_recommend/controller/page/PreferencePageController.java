package com.movie_ai_recommend.movie_ai_recommend.controller.page;

import com.movie_ai_recommend.movie_ai_recommend.dto.preference.PreferenceDto;
import com.movie_ai_recommend.movie_ai_recommend.service.preference.PreferenceService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Preference Page API
 *
 * -> 선호도 조사 페이지 렌더링 API
 */
@Controller
@RequiredArgsConstructor
public class PreferencePageController {

    private final PreferenceService preferenceService;

    @GetMapping("/preference")
    public String preferenceForm(@RequestParam(value = "userId", required = false) Long userId,
                                 HttpSession session, Model model) {
        if (userId != null) {
            session.setAttribute("userId", userId);
        } else {
            userId = (Long) session.getAttribute("userId");
        }
        model.addAttribute("userId", userId);
        return "preference/preference";
    }

    @GetMapping("/get")
    public String getPreference(@RequestParam(value = "userId", required = false) Long userId,
                                HttpSession session, Model model) {
        if (userId == null) {
            userId = (Long) session.getAttribute("userId");
        }
        PreferenceDto preference = preferenceService.getPreference(userId);
        model.addAttribute("preference", preference);
        model.addAttribute("userId", userId);
        return "preference/preference-view";
    }

    @GetMapping("/preference/update")
    public String preferenceUpdateForm(@RequestParam(value = "userId", required = false) Long userId,
                                       HttpSession session, Model model) {
        if (userId == null) {
            userId = (Long) session.getAttribute("userId");
        }
        PreferenceDto preference = preferenceService.getPreference(userId);
        model.addAttribute("preference", preference);
        model.addAttribute("userId", userId);
        return "preference/preference-update";
    }
}
