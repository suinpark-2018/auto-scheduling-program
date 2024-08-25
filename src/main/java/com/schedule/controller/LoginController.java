package com.schedule.controller;

import com.schedule.common.validation.ValidationGroups;
import com.schedule.dto.StaffDto;
import com.schedule.service.StaffServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final StaffServiceImpl staffService;

    @GetMapping("/form")
    public String moveToLoginPage() {
        return "login";
    }

    @PostMapping("in")
    public String login(@Validated(ValidationGroups.LoginCheckGroup.class) StaffDto staffDto, BindingResult bindingResult, boolean rememberMe, HttpServletResponse response, HttpServletRequest request, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorMsg = bindingResult.getAllErrors();
            ra.addFlashAttribute("errorMsg", errorMsg.get(0).getDefaultMessage());
            return "redirect:/login/form";
        }

        String id = staffDto.getId();

        // ID 기억하기 체크박스
        if (rememberMe) {
            Cookie cookie = new Cookie("savedId", id);
            response.addCookie(cookie); // ID 저장
        } else {
            Cookie cookie = new Cookie("savedId", "");
            cookie.setMaxAge(0); // ID 삭제
            response.addCookie(cookie);
        }

        if (!staffService.checkExistOfId(id)) {
            ra.addFlashAttribute("nonExistIdMsg", "존재하지 않는 아이디입니다.");
            return "redirect:/login/form";
        } else if (!staffService.validateStaffLogin(staffDto)) {
            ra.addFlashAttribute("notMatchMsg", "ID와 Password가 일치하지 않습니다.");
            return "redirect:/login/form";
        }

        String name = staffService.getStaffInfo(id).getName();
        String grade = staffService.getStaffInfo(id).getGrade();

        // 쿠키는 클라이언트 측에서 조작할 수 있는 위험성이 있음(보안에 취약)
        // 세션에 현재의 로그인 상태 및 사용자 아이디, 사용자 이름을 저장하여 관리
        HttpSession session = request.getSession();
        session.setAttribute("loginStatus", "Y");
        session.setAttribute("userId", id);
        session.setAttribute("userName", name);
        session.setAttribute("userGrade", grade);

        return "main";
    }

    @GetMapping("/out")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "index";
    }
}
