package com.schedule.controller;

import ch.qos.logback.classic.pattern.RelativeTimeConverter;
import com.schedule.common.validation.*;
import com.schedule.dto.StaffDto;
import com.schedule.service.StaffServiceImpl;
import com.schedule.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class SignUpController {
    private final StaffServiceImpl staffService;
    private final MailService mailService;

    @GetMapping("/verification")
    public String verification() {
        return "identityVerification";
    }

    @PostMapping("/verificationEmail")
    @ResponseBody
    public ResponseEntity<String> verificationEmail(@RequestBody @Validated(ValidationGroups.EmailCheckGroup.class) StaffDto staffDto, HttpServletRequest request) {
        // 사용자가 입력한 이메일
        String email = staffDto.getEmail();

        // 세션에 저장된 인증번호와 새로 생성한 인증번호 확인
        HttpSession session = request.getSession();
        String savedMailKey = (String) session.getAttribute("mailKey");
        String newMailKey = mailService.makeRandomMailKey();
        // 최종적으로 정해진 인증번호를 메일로 전송
        String mailKey = staffService.makeVerificationCode(savedMailKey, newMailKey);

        try {
            String selectedId = staffService.findIdByEmail(email);
            if (selectedId.isEmpty() || selectedId.isEmpty()) {
                if (staffService.sendVerificationEmail(email, mailKey)) {
                    session.setAttribute("mailKey", mailKey); // 세션에 인증번호 저장
                    session.setMaxInactiveInterval(60); // 세션 만료 시간 설정(1분)
                    return new ResponseEntity<>("인증번호 전송 성공", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("인증번호 전송에 실패했습니다.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("해당 이메일은 이미 사용 중입니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return new ResponseEntity<>("인증번호 전송에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 인증번호 일치여부 확인
    @PostMapping("/verificationCode")
    @ResponseBody
    public ResponseEntity<String> verificationCode(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        // 세션에 저장해둔 인증번호(savedMailKey)
        HttpSession session = request.getSession();
        String savedMailKey = (String) session.getAttribute("mailKey");

        // 사용자가 입력한 인증번호(inputMailKey)
        String inputMailKey = requestBody.get("inputMailKey");

        try {
            if (inputMailKey.equals(savedMailKey)) {
                return new ResponseEntity<>("인증 성공했습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("인증번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return new ResponseEntity<>("인증 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/form")
    public String moveToRegisterPage() {
        return "register";
    }

    @PostMapping("/checkDuplicatedId")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkDuplicatedId(@RequestBody @Validated(ValidationGroups.IdCheckGroup.class) StaffDto staffDto, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();

        // 유효성 검증에 실패한 경우
        if (bindingResult.hasErrors()) {
            response.put("status", "error");
            response.put("message", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String id = staffDto.getId();
        try {
            // 중복 아이디가 아닌 경우
            if (!staffService.checkDuplicatedId(id)) {
                response.put("status", "success");
                response.put("message", "사용 가능한 아이디입니다.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // 중복된 아이디인 경우
                response.put("status", "error");
                response.put("message", "이미 사용중인 아이디입니다.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "서버 오류가 발생했습니다.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/info")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getStaffRegisterInfo(@RequestBody @Validated(ValidationSequence.class) StaffDto staffDto, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            response.put("status", "error");
            response.put("message", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            try {
                staffService.saveStaffJoinInfo(staffDto);
                response.put("status", "success");
                response.put("message", "회원가입 성공!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                response.put("status", "error");
                response.put("message", "서버 오류 발생");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}