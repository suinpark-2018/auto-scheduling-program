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
            Cookie cookie = new Cookie("id", id);
            response.addCookie(cookie); // ID 저장
        } else {
            Cookie cookie = new Cookie("id", "");
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

        HttpSession session = request.getSession();
        session.setAttribute("loginState", "Y");
        session.setAttribute("userId", id);

        return "homepage";
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
